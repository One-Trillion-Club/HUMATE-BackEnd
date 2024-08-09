package com.otclub.humate.domain.member.dto;

import com.otclub.humate.common.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.text.SimpleDateFormat;

/**
 * 회원 프로필 Response DTO
 * @author 조영욱
 * @since 2024.08.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.04  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Builder
public class ProfileResponseDTO {
    // 회원 ID
    private String memberId;
    // 닉네임
    private String nickname;
    // 매너온도
    private double manner;
    // 한 줄 소개
    private String introduction;
    // 프로필 이미지 url
    private String profileImgUrl;
    // 성별
    private String gender;
    // 생일
    private String birthdate;

    public static ProfileResponseDTO from(Member member) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String birthdate = format.format(member.getBirthdate());
        return ProfileResponseDTO.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .manner(member.getManner())
                .introduction(member.getIntroduction())
                .profileImgUrl(member.getProfileImgUrl())
                .gender(member.getGender())
                .birthdate(birthdate)
                .build();
    }
}
