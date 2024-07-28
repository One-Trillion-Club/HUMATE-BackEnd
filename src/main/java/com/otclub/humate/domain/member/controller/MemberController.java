package com.otclub.humate.domain.member.controller;

import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 컨트롤러
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
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    /**
     * 닉네임 중복 체크
     *
     * @author 조영욱
     * @param nickname 중복 확인 할 닉네임
     */
    @GetMapping("/check-nickname")
    public ResponseEntity<CommonResponseDTO> checkDuplicatedNickname(@RequestParam("nickname") String nickname) {
        return service.checkAvailableNickname(nickname) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "사용 가능한 닉네임입니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "이미 사용중인 닉네임입니다."));

    }
}
