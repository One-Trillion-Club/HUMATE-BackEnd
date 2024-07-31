package com.otclub.humate.domain.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostListResponseDTO {

    // 게시글 고유번호
    private int postId;
    // 회원 닉네임
    private String nickname;
    // 회원 프로필 이미지
    private String profileImgUrl;
    // 태그 내용 (list 형태 - ["기타", "악세사리"]
    private List<String> tags;
    // 게시글 제목
    private String title;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 매칭 성별
    private int matchGender;
    // 매칭 언어
    private String matchLanguage;
    // 작성 날짜
    private Date createdAt;
    // 매칭 여부
    private int isMatched;

}
