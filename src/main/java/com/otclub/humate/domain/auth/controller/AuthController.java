package com.otclub.humate.domain.member.controller;

import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.common.entity.Member;
import com.otclub.humate.domain.member.dto.SignUpRequestDTO;
import com.otclub.humate.domain.member.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
