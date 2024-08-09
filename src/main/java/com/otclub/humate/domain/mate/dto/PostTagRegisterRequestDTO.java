package com.otclub.humate.domain.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 매칭글 태그 정보 등록 Request DTO
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
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostTagRegisterRequestDTO {

    // 태그 ID
    private int tagId;

}
