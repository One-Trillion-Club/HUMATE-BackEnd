package com.otclub.humate.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 사용자 회원가입 Request DTO
 * @author 조영욱
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
@ToString
public class SignUpRequestDTO {
    // 국적 코드
    private int nationality;
    // 로그인 아이디
    private String loginId;
    // 비밀번호
    private String password;
    // 성별
    private String gender;
    // 생일
    private String birthdate;
    // 닉네임
    private String nickname;
    // 한 줄 소개
    private String introduction;
    // 프로필 이미지 url
    private String profileImgUrl;
    // 가능한 언어
    private String language;
    // 전화번호 (한국인만)
    private String phone;
    // 여권번호 (외국인만)
    private String passportNo;
    // 핸드폰/여권 인증 성공 시 발급한 인증 성공 보증 코드
    private String verifyCode;
}
