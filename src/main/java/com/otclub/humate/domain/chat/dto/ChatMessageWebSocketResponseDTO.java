package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.ChatMessage;
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
public class ChatMessageWebSocketResponseDTO {
    private ChatRoomDetailDTO chatRoomDetailDTO;
    private ChatMessage chatMessage;


    public static ChatMessageWebSocketResponseDTO of(ChatRoomDetailDTO chatRoomDetailDTO, ChatMessage chatMessage) {
        return ChatMessageWebSocketResponseDTO.builder()
                .chatRoomDetailDTO(chatRoomDetailDTO)
                .chatMessage(chatMessage)
                .build();
    }
}