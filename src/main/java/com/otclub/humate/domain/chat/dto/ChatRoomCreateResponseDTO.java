package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
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
public class ChatRoomCreateResponseDTO {
    private String chatRoomId;
    private String participateId;

    public static ChatRoomCreateResponseDTO of(int chatRoomId, int participateId){
        return ChatRoomCreateResponseDTO.builder()
                .chatRoomId(String.valueOf(chatRoomId))
                .participateId(String.valueOf(participateId))
                .build();
    }
}
