package com.otclub.humate.domain.chat.dto;

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
public class RoomCreateResponseDTO {
    private String chatRoomId;
    private String participateId;

    public static RoomCreateResponseDTO of(int chatRoomId, int participateId){
        return RoomCreateResponseDTO.builder()
                .chatRoomId(String.valueOf(chatRoomId))
                .participateId(String.valueOf(participateId))
                .build();
    }
}
