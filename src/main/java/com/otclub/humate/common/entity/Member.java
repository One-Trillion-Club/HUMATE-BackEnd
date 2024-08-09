package com.otclub.humate.common.entity;

import lombok.*;

import java.util.Date;

/**
 * 회원 Entity
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
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    // 회원 ID
    private String memberId;
    // 국적 코드
    private int nationality;
    // 로그인 아이디
    private String loginId;
    // 비밀번호
    private String password;
    // 관리자 여부
    private boolean isAdmin;
    // 성별
    private String gender;
    // 생일
    private Date birthdate;
    // 닉네임
    private String nickname;
    // 한 줄 소개
    private String introduction;
    // 프로필 이미지 url
    private String profileImgUrl;
    // 남은 포인트 양
    private int point;
    // 매너온도
    private double manner;
    // 가능한 언어
    private String language;
    // 회원 상태
    private int status;
    // 회원가입일
    private Date createdAt;
    // 회원탈퇴일
    private Date deletedAt;
    // 현재 발급된 리프레시 토큰
    private String refreshToken;
}
