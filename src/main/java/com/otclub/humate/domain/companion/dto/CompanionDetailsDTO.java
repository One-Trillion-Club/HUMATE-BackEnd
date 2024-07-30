package com.otclub.humate.domain.companion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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
}
