package com.otclub.humate.domain.auth.controller;

import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.auth.constant.AuthConstant;
import com.otclub.humate.domain.auth.dto.LogInRequestDTO;
import com.otclub.humate.domain.auth.dto.SignUpRequestDTO;
import com.otclub.humate.domain.auth.jwt.JwtDTO;
import com.otclub.humate.domain.auth.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.otclub.humate.domain.auth.constant.AuthConstant.*;

/**
 * 인증/인가 컨트롤러
 * @author 조영욱
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	조영욱        최초 생성
 * </pre>
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    /**
     * 새 사용자 회원가입
     * 로그인 id, 닉네임 중복 시 실패한다.
     *
     * @author 조영욱
     * @param dto 회원가입 사용자 정보
     *
     */
    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDTO> signUp(@RequestBody SignUpRequestDTO dto) {

        return service.signUp(dto) == 1 ?
                ResponseEntity.ok(new CommonResponseDTO(true, "회원가입 성공")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "회원가입 실패"));
    }

    /**
     * loginId 중복 체크
     *
     * @author 조영욱
     * @param loginId 중복 확인 할 id
     */
    @GetMapping("/check-loginid")
    public ResponseEntity<CommonResponseDTO> checkDuplicatedLoginId(@RequestParam("loginId") String loginId) {
        return service.checkAvailableLoginId(loginId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "사용 가능한 아이디입니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "이미 사용중인 아이디입니다."));

    }

    /**
     * 회원 로그인
     * JWT 토큰을 발급하고 클라이언트의 쿠키에 저장
     *
     * @author 조영욱
     * @param dto 회원 로그인 아이디, 비밀번호
     * @param response
     */
    @PostMapping("/login")
    public ResponseEntity<CommonResponseDTO> logIn(@RequestBody LogInRequestDTO dto, HttpServletResponse response) {
        JwtDTO jwtDTO = service.logIn(dto);

        if (jwtDTO == null) {
            return ResponseEntity.ok(new CommonResponseDTO(false, "로그인 실패"));
        }

        Cookie accessToken = new Cookie("ajt", jwtDTO.accessToken());
        accessToken.setMaxAge(JWT_TOKEN_COOKIE_MAX_AGE);
        accessToken.setHttpOnly(true);
        accessToken.setPath("/");

        Cookie refreshToken = new Cookie("rjt", jwtDTO.refreshToken());
        refreshToken.setMaxAge(JWT_TOKEN_COOKIE_MAX_AGE);
        refreshToken.setHttpOnly(true);
        refreshToken.setPath("/");

        response.addCookie(accessToken);
        response.addCookie(refreshToken);

        return ResponseEntity.ok(new CommonResponseDTO(true, "로그인 성공"));
    }
}