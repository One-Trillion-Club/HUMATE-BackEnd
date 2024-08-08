package com.otclub.humate.domain.chat.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanionStartRequestDTO {
    private String chatRoomId;
}