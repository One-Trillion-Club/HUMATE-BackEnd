package com.otclub.humate.domain.member.controller;

import com.otclub.humate.common.annotation.MemberId;
import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.member.dto.MateDetailResponseDTO;
import com.otclub.humate.domain.member.dto.ModifyProfileRequestDTO;
import com.otclub.humate.domain.member.dto.ProfileResponseDTO;
import com.otclub.humate.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/members")
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

    /**
     * 내 정보 조회
     *
     * @author 조영욱
     */
    @GetMapping("/profile")
    public ResponseEntity<ProfileResponseDTO> getMyProfile(@MemberId String memberId) {
        return ResponseEntity.ok(service.getMyProfile(memberId));
    }

    /**
     * 내 정보 수정
     *
     * @author 조영욱
     */
    @PutMapping("/profile")
    public ResponseEntity<CommonResponseDTO> modifyMyProfile(
            @RequestBody ModifyProfileRequestDTO dto, @MemberId String memberId) {
        return service.modifyMyProfile(dto, memberId) ?
                ResponseEntity.ok(new CommonResponseDTO(true, "수정에 성공하였습니다.")) :
                ResponseEntity.ok(new CommonResponseDTO(false, "수정에 실패하였습니다."));
    }

    /**
     * 내 메이트 조회
     *
     * @author 조영욱
     * @param memberId
     */
    @GetMapping("/my-mates")
    public ResponseEntity<List<MateDetailResponseDTO>> getMyMates(@MemberId String memberId) {
        return ResponseEntity.ok(service.getMyMates(memberId));
    }

//    /**
//     * 상대방 프로필 조회
//     */
//    @GetMapping("/{memberId}")
//    public ResponseEntity<>

    // 글 작성 내역 조회
//    @GetMapping("/my-posts")

    // 포인트 사용 내역 조회
//    @GetMapping("/my-points")

    // 회원 탈퇴
//    @DeleteMapping("/")
}
