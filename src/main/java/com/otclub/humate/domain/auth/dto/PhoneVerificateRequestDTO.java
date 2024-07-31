package com.otclub.humate.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneVerificateRequestDTO {

    private String phone;
    private String code;
}
