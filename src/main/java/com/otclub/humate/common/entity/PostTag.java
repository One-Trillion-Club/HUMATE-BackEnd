package com.otclub.humate.common.entity;

import lombok.*;

/**
 * 매칭글 태그 Entity
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
public class PostTag {

    // 매칭글 태그 고유번호
    private int postTagId;
    // 글 고유번호
    private int postId;
    // 태그 고유번호
    private int tagId;

}
