package com.otclub.humate.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 채팅방 생성 응답 DTO
 * @author 최유경
 * @since 2024.08.05
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.05  	최유경        최초 생성
 * </pre>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomCreateResponseDTO {
    // 채팅방 ID
    private String chatRoomId;
    // 참여 ID
    private String participateId;

    public static RoomCreateResponseDTO of(int chatRoomId, int participateId){
        return RoomCreateResponseDTO.builder()
                .chatRoomId(String.valueOf(chatRoomId))
                .participateId(String.valueOf(participateId))
                .build();
    }
}
