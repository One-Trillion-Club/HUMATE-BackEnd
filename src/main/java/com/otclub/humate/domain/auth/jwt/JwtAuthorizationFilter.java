package com.otclub.humate.domain.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.common.exception.ErrorResponse;
import com.otclub.humate.domain.auth.service.AuthService;
import com.otclub.humate.domain.auth.util.AuthUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 필터 클래스
 * @author 조영욱
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	조영욱        최초 생성
 * 2024.07.30   조영욱        유틸 모듈 생성을 통한 리팩토링
 * </pre>
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtTokenProvider;
    private final AuthService authService;

    /**
     * 인가 필요 메소드 실행 시 먼저 실행 될 필터 메소드
     *
     * @author 조영욱
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     */
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException {
        // JWT 토큰 추출
        JwtDTO jwtDTO = resolveToken(request);
        // validateToken으로 토큰 유효성 검사
        try {
            if (jwtDTO == null) {
                throw new Exception();
            }
            // 토큰 validate
            jwtTokenProvider.validateToken(jwtDTO.accessToken());

            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(jwtDTO.accessToken());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("\n===Security Filter===\n\n"+authentication+"\n\n");

            request.setAttribute("memberId", authentication.getName());
            chain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            // 엑세스 토큰 만료 시 refresh token을 통한 토큰 재발급
            try {
                String refreshToken = jwtDTO.refreshToken();

                jwtTokenProvider.validateToken(refreshToken);
                Claims claims = jwtTokenProvider.parseClaims(refreshToken);
                String memberId = claims.getSubject();

                JwtDTO refreshedTokenDTO = authService.refreshJwtToken(memberId, refreshToken);

                Cookie accessTokenCookie = AuthUtil.createJwtTokenCookie("ajt", refreshedTokenDTO.accessToken());
                Cookie refreshTokenCookie = AuthUtil.createJwtTokenCookie("rjt", refreshedTokenDTO.refreshToken());

                response.addCookie(accessTokenCookie);
                response.addCookie(refreshTokenCookie);

                // Token Refresh 시에도 Authentication 등록
                Authentication authentication = jwtTokenProvider.getAuthentication(refreshedTokenDTO.accessToken());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                request.setAttribute("memberId", authentication.getName());

                log.info("\n\n===토큰 리프레시 완료===\n");
                chain.doFilter(request, response);

            } catch (Exception e2) {
                setErrorResponse(response, ErrorCode.FORBIDDEN_REQUEST);
            }

        } catch (Exception e) {
            setErrorResponse(response, ErrorCode.FORBIDDEN_REQUEST);
        }
    }

    /**
     * request의 cookie에서 JWT 토큰 찾아 반환
     *
     * @author 조영욱
     * @param request
     * @return JwtDTO
     */
    private JwtDTO resolveToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String accessToken = null;
        String refreshToken = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("ajt".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                } else if ("rjt".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                }

                if (accessToken != null && refreshToken != null) {
                    return new JwtDTO(accessToken, refreshToken);
                }
            }
        }
        return null;
    }

    /**
     * Security Chain 에서 발생하는 에러 응답 구성
     *
     * @author 조영욱
     * @param response
     * @param errorCode
     * @throws IOException
     */
    public static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus());
        ObjectMapper objectMapper = new ObjectMapper();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .errorCode(errorCode.name())
                .message(errorCode.getMessage())
                .build();

        log.error("\n\nJWT 토큰 에러입니다. JWT 토큰 에러입니다.\n\n");
        // 토큰 에러 시 로그아웃 시킴
        Cookie accessTokenDeleteCookie = AuthUtil.createJwtTokenDeleteCookie("ajt");
        Cookie refreshTokenDeleteCookie = AuthUtil.createJwtTokenDeleteCookie("rjt");

        response.addCookie(accessTokenDeleteCookie);
        response.addCookie(refreshTokenDeleteCookie);

        ResponseEntity<ErrorResponse> error =
                new ResponseEntity<> (errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
        String s = objectMapper.writeValueAsString(error);

        response.getWriter().write(s);
    }
}
