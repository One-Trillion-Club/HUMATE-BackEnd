package com.otclub.humate.common.entity;

import lombok.*;

/**
 * 매칭 장소 Entity
 * @author 김지현
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
@Getter
public class PostPlace {

    // 장소 ID
    private int postPlaceId;
    // 글 ID
    private int postId;
    // 장소 타입 (1-매장, 2-팝업스토어)
    private int type;
    // 장소 이름
    private String name;

}
