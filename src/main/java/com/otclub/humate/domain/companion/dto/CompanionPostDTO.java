package com.otclub.humate.domain.companion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 동행 게시글 정보 dto
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
public class CompanionPostDTO {
    private int companionId;
    private String postTitle;
    private String matchBranch;
    private String matchDate;
    private String firstMemberId;
    private String firstMemberNickname;
    private String secondMemberId;
    private String secondMemberNickname;

}
