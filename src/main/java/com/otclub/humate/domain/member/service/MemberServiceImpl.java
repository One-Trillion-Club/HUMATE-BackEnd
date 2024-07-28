package com.otclub.humate.domain.member.service;

import com.otclub.humate.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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
}
