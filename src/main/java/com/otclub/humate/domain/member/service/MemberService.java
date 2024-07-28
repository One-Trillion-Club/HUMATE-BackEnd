package com.otclub.humate.domain.member.service;

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
 * </pre>
 */
public interface MemberService {
    boolean checkAvailableNickname(String nickname);
}
