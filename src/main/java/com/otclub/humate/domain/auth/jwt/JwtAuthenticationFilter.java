package com.otclub.humate.domain.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.common.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * JWT 필터 클래스
 * @author 조영욱
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	조영욱        최초 생성
 * </pre>
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtTokenProvider;

    /**
     * 인가 필요 메소드 실행 시 먼저 실행 될 필터 메소드
     *
     * @author 조영욱
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // JWT 토큰 추출
        JwtDTO jwtDTO = resolveToken((HttpServletRequest) request);
        // validateToken으로 토큰 유효성 검사
        try {
            if (jwtDTO != null && jwtTokenProvider.validateToken(jwtDTO.accessToken())) {
                // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(jwtDTO.accessToken());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                log.info("===Security Filter===");
                chain.doFilter(request, response);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            setErrorResponse((HttpServletResponse) response, ErrorCode.FORBIDDEN_REQUEST);
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
    public static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode)
            throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus());
        ObjectMapper objectMapper = new ObjectMapper();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(errorCode.getStatus())
                .errorCode(errorCode.name())
                .message(errorCode.getMessage())
                .build();

        ResponseEntity<ErrorResponse> error =
                new ResponseEntity<> (errorResponse, HttpStatus.valueOf(errorCode.getStatus()));
        String s = objectMapper.writeValueAsString(error);

        response.getWriter().write(s);
    }
}
