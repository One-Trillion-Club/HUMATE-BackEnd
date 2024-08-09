package com.otclub.humate.domain.companion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 동행 상세 정보 dto
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanionDetailsDTO {
    // 동행 ID
    private int companionId;
    // 동행 종료 상태
    private Date finishedAt;
    // 첫 번째 회원 ID
    private String firstMemberId;
    // 첫 번째 회원 프로필 이미지 url
    private String firstMemberProfileImgUrl;
    // 첫 번째 회원 닉네임
    private String firstMemberNickname;
    // 두 번째 회원 ID
    private String secondMemberId;
    // 두 번째 회원 프로필 이미지 url
    private String secondMemberProfileImgUrl;
    // 두 번째 회원 닉네임
    private String secondMemberNickname;
    // 매칭글 제목
    private String postTitle;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
}
