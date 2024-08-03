package com.otclub.humate.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 여권 인증 Request DTO
 * @author 조영욱
 * @since 2024.08.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.01  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
public class PassportVerificationRequestDTO {

    private String organization = "0001";
    private String birthdate;
    private String nationality;
    private String country;
    private String passportNo;
}
