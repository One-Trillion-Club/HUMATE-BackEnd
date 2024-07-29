package com.otclub.humate.domain.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostTagRegisterRequestDTO {

    // 태그 고유번호
    private int tagId;

}
