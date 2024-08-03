package com.otclub.humate.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 사용자 회원가입 Request DTO
 * @author 조영욱
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
@ToString
public class SignUpRequestDTO {

    private int nationality;
    private String loginId;
    private String password;
    private String gender;
    private String birthdate;
    private String nickname;
    private String introduction;
    private String profileImgUrl;
    private String language;
    private String phone;
    private String passportNo;
    private String verifyCode;
}
