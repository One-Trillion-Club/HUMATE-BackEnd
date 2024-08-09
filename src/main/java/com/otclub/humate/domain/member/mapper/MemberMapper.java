package com.otclub.humate.domain.member.mapper;

import com.otclub.humate.common.entity.Member;
import com.otclub.humate.domain.auth.dto.SignUpRequestDTO;
import com.otclub.humate.domain.member.dto.MateDetailResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * 회원 매퍼 인터페이스
 * @author 조영욱
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	조영욱        최초 생성
 * </pre>
 */
@Mapper
public interface MemberMapper {

    public int insertMember(SignUpRequestDTO dto);
    public Member selectMemberByLoginId(String loginId);
    public Member selectMemberByNickname(String nickname);
    public int updateRefreshToken(Member member);
    public Optional<Member> selectMemberById(String memberId);
    public Member selectMemberByPhone(String phone);
    public Optional<Member> selectMemberDetail(String memberId);
    public int updateMember(Member member);
    public List<MateDetailResponseDTO> selectMatesByMemberId(String memberId);
}
