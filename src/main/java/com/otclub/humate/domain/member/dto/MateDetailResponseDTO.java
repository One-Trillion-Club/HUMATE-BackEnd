package com.otclub.humate.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateDetailResponseDTO {
    private String memberId;
    private String profileImgUrl;
    private String nickname;
    private String gender;
    private String matchingDate;
}
