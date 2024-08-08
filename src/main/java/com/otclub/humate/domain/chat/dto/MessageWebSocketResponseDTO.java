package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageWebSocketResponseDTO {
    private RoomDetailDTO roomDetailDTO;
    private Message message;


    public static MessageWebSocketResponseDTO of(RoomDetailDTO roomDetailDTO, Message message) {
        return MessageWebSocketResponseDTO.builder()
                .roomDetailDTO(roomDetailDTO)
                .message(message)
                .build();
    }
}