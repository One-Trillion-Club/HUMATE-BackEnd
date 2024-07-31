package com.otclub.humate.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GeneratePhoneVerificationCodeResponseDTO {

    private String code;
}
