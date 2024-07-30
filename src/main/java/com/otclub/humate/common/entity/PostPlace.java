package com.otclub.humate.common.entity;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
@Getter
public class PostPlace {

    // 장소 고유번호
    private int postPlaceId;
    // 글 고유번호
    private int postId;
    // 장소 타입 (1-매장, 2-팝업스토어)
    private int type;
    // 장소 이름
    private String name;

}
