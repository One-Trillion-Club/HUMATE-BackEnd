package com.otclub.humate.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 메이트 상세 정보 Response DTO
 * @author 조영욱
 * @since 2024.08.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.04  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
public class MateDetailResponseDTO {
    // 회원 ID
    private String memberId;
    // 프로필 이미지 url
    private String profileImgUrl;
    // 닉네임
    private String nickname;
    // 성별
    private String gender;
    // 매칭된 날짜
    private String matchingDate;
}
