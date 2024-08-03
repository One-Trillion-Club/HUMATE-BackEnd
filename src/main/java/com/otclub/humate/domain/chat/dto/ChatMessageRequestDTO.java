package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 채팅 메세지 요청 DTO
 * @author 최유경
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	최유경        최초 생성
 * </pre>
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessageRequestDTO {
    private String chatRoomId;
    private String senderId;
    private String content;
    private MessageType messageType;
}
