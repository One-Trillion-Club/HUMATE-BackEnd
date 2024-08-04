package com.otclub.humate.domain.member.dto;

import com.otclub.humate.common.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ProfileResponseDTO {
    private String memberId;
    private String nickname;
    private double manner;
    private String introduction;
    private String profileImgUrl;

    public static ProfileResponseDTO from(Member member) {
        return ProfileResponseDTO.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .manner(member.getManner())
                .introduction(member.getIntroduction())
                .profileImgUrl(member.getProfileImgUrl())
                .build();
    }
}
