package com.otclub.humate.domain.companion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
