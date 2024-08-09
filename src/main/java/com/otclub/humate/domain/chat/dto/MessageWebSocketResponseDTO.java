package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 웹소켓 메세지 실시간 전송을 위한 DTO
 * @author 최유경
 * @since 2024.08.08
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.08  	최유경        최초 생성
 * </pre>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageWebSocketResponseDTO {
    // 채팅방 상세 DTO
    private RoomDetailDTO roomDetailDTO;
    // 채팅 메세지
    private Message message;


    public static MessageWebSocketResponseDTO of(RoomDetailDTO roomDetailDTO, Message message) {
        return MessageWebSocketResponseDTO.builder()
                .roomDetailDTO(roomDetailDTO)
                .message(message)
                .build();
    }
}