package com.otclub.humate.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MateCreateRequestDTO {
    private String chatRoomId;
    private String memberId;
    private int isClicked;
}
