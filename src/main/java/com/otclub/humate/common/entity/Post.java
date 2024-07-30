package com.otclub.humate.common.entity;

import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
@Getter
public class Post {

    // 게시글 고유번호
    private int postId;
    // 회원 고유번호
    private String memberId;
    // 게시글 제목
    private String title;
    // 게시글 내용
    private String content;
    // 매칭 날짜
    @Nullable
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 매칭 성별
    private int matchGender;
    // 매칭 언어
    private String matchLanguage;
    // 작성 날짜
    private Date createdAt;
    // 삭제 날짜
    private Date deletedAt;
    // 매칭 여부
    private int isMatched;
}
