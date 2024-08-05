package com.otclub.humate.domain.member.service;

import com.otclub.humate.domain.member.dto.MateDetailResponseDTO;
import com.otclub.humate.domain.member.dto.ModifyProfileRequestDTO;
import com.otclub.humate.domain.member.dto.ProfileResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 회원 서비스 인터페이스
 * @author 조영욱
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	조영욱        최초 생성
 * 2024.08.04   조영욱        마이페이지 메소드 추가
 * </pre>
 */
public interface MemberService {
    boolean checkAvailableNickname(String nickname);
    ProfileResponseDTO getMemberProfile(String memberId);
    boolean modifyMyProfile(ModifyProfileRequestDTO dto, MultipartFile image, String memberId);
    List<MateDetailResponseDTO> getMyMates(String memberId);
}
