package com.otclub.humate.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomCreateRequestDTO {
    private int postId;
    private int memberId;
    private int writerId;
}
