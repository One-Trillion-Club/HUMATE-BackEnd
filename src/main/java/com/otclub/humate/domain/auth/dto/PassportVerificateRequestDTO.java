package com.otclub.humate.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassportVerificateRequestDTO {

    private String organization = "0001";
    private String birthDate;
    private String nationality;
    private String country;
    private String passportNo;
}
