package com.otclub.humate.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratePhoneVerificationCodeRequestDTO {

    private String phone;
}
