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
    private int companionId;
    private Date finishedAt;
    private String firstMemberId;
    private String firstMemberProfileImgUrl;
    private String firstMemberNickname;
    private String secondMemberId;
    private String secondMemberProfileImgUrl;
    private String secondMemberNickname;
    private String postTitle;
    private String matchDate;
    private String matchBranch;
}
