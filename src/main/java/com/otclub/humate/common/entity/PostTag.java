package com.otclub.humate.common.entity;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
@Getter
public class PostTag {

    // 매칭글 태그 고유번호
    private int postTagId;
    // 글 고유번호
    private int postId;
    // 태그 고유번호
    private int tagId;

}
