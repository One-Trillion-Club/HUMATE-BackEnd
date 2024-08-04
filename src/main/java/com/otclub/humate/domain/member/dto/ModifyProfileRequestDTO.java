package com.otclub.humate.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyProfileRequestDTO {
    private String nickname;
    private String introduction;
    private String profileImgUrl;
}
