package com.otclub.humate.domain.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostPlaceDetailResponseDTO {

    // 장소 타입 (1-매장, 2-팝업스토어)
    private int type;
    // 장소 이름
    private String name;

}
