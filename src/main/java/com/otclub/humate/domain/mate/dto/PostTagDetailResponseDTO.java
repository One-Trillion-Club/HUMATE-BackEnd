package com.otclub.humate.domain.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostTagDetailResponseDTO {

    // 카테고리
    private String category;
    // 태그 이름
    private String name;

}
