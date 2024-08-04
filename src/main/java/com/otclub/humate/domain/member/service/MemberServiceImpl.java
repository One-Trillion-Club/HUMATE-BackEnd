package com.otclub.humate.domain.member.service;

import com.otclub.humate.common.entity.Member;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.member.dto.MateDetailResponseDTO;
import com.otclub.humate.domain.member.dto.ModifyProfileRequestDTO;
import com.otclub.humate.domain.member.dto.ProfileResponseDTO;
import com.otclub.humate.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 회원 서비스 구현체
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
@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper mapper;

    /**
     * 닉네임 중복 체크
     *
     * @author 조영욱
     * @param nickname 중복 확인 할 닉네임
     */
    @Override
    public boolean checkAvailableNickname(String nickname) {
        return mapper.selectMemberByNickname(nickname) == null;
    }

    /**
     * 내 정보 조회
     *
     * @author 조영욱
     * @param memberId 조회할 멤버 아이디
     */
    @Override
    public ProfileResponseDTO getMyProfile(String memberId) {
        Member member = mapper.selectMemberDetail(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_MEMBER));

        return ProfileResponseDTO.from(member);
    }

    /**
     * 내 정보 수정
     *
     * @author 조영욱
     */
    @Override
    public boolean modifyMyProfile(ModifyProfileRequestDTO dto, String memberId) {
        Member member = mapper.selectMemberById(memberId).
                orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_MEMBER));

        // 닉네임 중복
        if (dto.getNickname() != null && !checkAvailableNickname(dto.getNickname())) {
            throw new CustomException(ErrorCode.DUPLICATE_KEY);
        }
        member.setNickname(dto.getNickname());
        member.setIntroduction(dto.getIntroduction());
        member.setProfileImgUrl(dto.getProfileImgUrl());

        return mapper.updateMember(member) == 1;
    }

    /**
     * 내 메이트 조회
     *
     * @author 조영욱
     */
    public List<MateDetailResponseDTO> getMyMates(String memberId) {
        return mapper.selectMatesByMemberId(memberId);
    }
}
