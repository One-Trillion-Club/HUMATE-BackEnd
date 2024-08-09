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
    // 회원 추가
    public int insertMember(SignUpRequestDTO dto);

    // 로그인 아이디로 회원 검색
    public Member selectMemberByLoginId(String loginId);

    // 닉네임으로 회원 검색
    public Member selectMemberByNickname(String nickname);

    // 리프레시 토큰 업데이트
    public int updateRefreshToken(Member member);

    // 회원 아이디로 회원 검색
    public Optional<Member> selectMemberById(String memberId);

    // 휴대전화 번호로 회원 검색
    public Member selectMemberByPhone(String phone);

    // 회원 디테일 조회
    public Optional<Member> selectMemberDetail(String memberId);

    // 회원 수정
    public int updateMember(Member member);

    // 회원 아이디로 메이트 리스트 검색
    public List<MateDetailResponseDTO> selectMatesByMemberId(String memberId);
}
