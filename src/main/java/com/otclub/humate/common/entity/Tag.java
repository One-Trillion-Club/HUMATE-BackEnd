package com.otclub.humate.common.entity;

import lombok.*;

/**
 * 태그 Entity
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
public class Tag {

    // 태그 ID
    private int tagId;
    // 카테고리
    private int category;
    // 태그 이름
    private int name;

}
