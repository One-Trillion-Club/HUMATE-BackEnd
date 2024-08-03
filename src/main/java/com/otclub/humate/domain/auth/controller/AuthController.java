package com.otclub.humate.domain.auth.controller;

import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.auth.dto.*;
import com.otclub.humate.domain.auth.jwt.JwtDTO;
import com.otclub.humate.domain.auth.service.AuthService;
import com.otclub.humate.domain.auth.util.AuthUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
 * 2024.07.30   조영욱        유틸 모듈 생성을 통한 리팩토링
 * 2024.07.31   조영욱        휴대폰 인증 추가
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
    @PostMapping(value="/signup", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CommonResponseDTO> signUp(
            @RequestPart("signUpRequestDTO") SignUpRequestDTO dto,
            @RequestPart(value="image", required=false) MultipartFile image) {

        return service.signUp(dto, image) == 1 ?
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

        Cookie accessTokenCookie = AuthUtil.createJwtTokenCookie("ajt", jwtDTO.accessToken());
        Cookie refreshTokenCookie = AuthUtil.createJwtTokenCookie("rjt", jwtDTO.refreshToken());

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok(new CommonResponseDTO(true, "로그인 성공"));
    }

    /**
     * 회원 로그아웃
     * JWT 토큰을 클라이언트 쿠키에서 지워 로그아웃시킨다.
     *
     * @author 조영욱
     */
    @PostMapping("/logout")
    public ResponseEntity<CommonResponseDTO> logOut(HttpServletResponse response) {
        Cookie accessTokenDeleteCookie = AuthUtil.createJwtTokenDeleteCookie("ajt");
        Cookie refreshTokenDeleteCookie = AuthUtil.createJwtTokenDeleteCookie("rjt");

        response.addCookie(accessTokenDeleteCookie);
        response.addCookie(refreshTokenDeleteCookie);

        return ResponseEntity.ok(new CommonResponseDTO(true, "로그아웃 성공"));
    }

    /**
     * 휴대폰 인증 번호 생성
     *
     * @author 조영욱
     */
    @PostMapping("/phone/code")
    public ResponseEntity<CommonResponseDTO> generatePhoneVerificationCode(
            @RequestBody GeneratePhoneVerificationCodeRequestDTO dto) {
        String code = service.generatePhoneVerificationCode(dto);

        return ResponseEntity.ok(new CommonResponseDTO(true, "인증 번호 생성 완료"));
    }

    /**
     * 휴대폰 번호 인증
     *
     * @author 조영욱
     * @return 성공 시 인증 성공을 보증하기 위한 key 리턴, 실패 시 null
     */
    @PostMapping("/phone/verification")
    public ResponseEntity<CommonResponseDTO> verifyPhone(
            @RequestBody PhoneVerificationRequestDTO dto) {
        String result = service.verifyPhone(dto);
         return result != null ?
            ResponseEntity.ok(new CommonResponseDTO(true, result)) :
            ResponseEntity.ok(new CommonResponseDTO(false, "휴대폰 번호 인증 실패"));
    }

    /**
     * 여권 번호 인증
     *
     * @author 조영욱
     * @return 성공 시 인증 성공을 보증하기 위한 key 리턴, 실패 시 null
     */
    @PostMapping("/passport/verification")
    public ResponseEntity<CommonResponseDTO> verifyPassport(
            @RequestBody PassportVerificationRequestDTO dto) {
        String result = service.verifyPassport(dto);
        return result != null ?
                ResponseEntity.ok(new CommonResponseDTO(true, result)) :
                ResponseEntity.ok(new CommonResponseDTO(false, "여권 인증 실패"));
    }

}
