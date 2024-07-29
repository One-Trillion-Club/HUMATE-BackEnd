package com.otclub.humate.common.entity;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
@Getter
public class Tag {

    // 태그 고유번호
    private int tagId;
    // 카테고리
    private int category;
    // 태그 이름
    private int name;

}
