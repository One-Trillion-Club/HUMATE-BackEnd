package com.otclub.humate.domain.member.service;

import com.otclub.humate.domain.member.dto.MateDetailResponseDTO;
import com.otclub.humate.domain.member.dto.ModifyProfileRequestDTO;
import com.otclub.humate.domain.member.dto.ProfileResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 회원 서비스 인터페이스
 * @author 조영욱
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	조영욱        최초 생성
 * 2024.08.04   조영욱        마이페이지 메소드 추가
 * </pre>
 */
public interface MemberService {
    /**
     * 닉네임 중복 체크
     *
     * @author 조영욱
     * @param nickname 중복 확인 할 닉네임
     */
    boolean checkAvailableNickname(String nickname);

    /**
     * 내 정보 조회
     *
     * @author 조영욱
     * @param memberId 조회할 멤버 아이디
     */
    ProfileResponseDTO getMemberProfile(String memberId);

    /**
     * 내 정보 수정
     *
     * @author 조영욱
     */
    boolean modifyMyProfile(ModifyProfileRequestDTO dto, MultipartFile image, String memberId);

    /**
     * 메이트 프로필 조회
     *
     * @author 조영욱
     */
    List<MateDetailResponseDTO> getMyMates(String memberId);
}
