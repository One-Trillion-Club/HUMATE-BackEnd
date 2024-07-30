package com.otclub.humate.domain.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailResponseDTO {

    // 게시글 고유번호
    private int postId;
    // 회원 고유번호
    private String memberId;
    // 게시글 제목
    private String title;
    // 게시글 내용
    private String content;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 매칭 성별
    private int matchGender;
    // 매칭 언어
    private String matchLanguage;
    // 매칭 여부
    private int isMatched;
    // 매장 및 팝업스토어
    private List<PostPlaceDetailResponseDTO> postPlaces;
    // 매칭글 태그
    private List<PostTagDetailResponseDTO> postTags;

}
