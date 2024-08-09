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
    // 0001로 고정
    private String organization = "0001";
    // "yyyymmdd" 형식
    private String birthDate;
    // codef에서 제공하는 국적표 참고
    private String nationality;
    // codef에서 제공하는 나라이름 참고
    private String country;
    // 여권번호
    private String passportNo;
}
