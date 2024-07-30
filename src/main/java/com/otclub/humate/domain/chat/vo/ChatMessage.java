package com.otclub.humate.domain.chat.vo;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 채팅메세지 MongoDB Document
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

@Document(collection = "chatMessage")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    @Id
    private String _id;
    @Indexed
    private int chatRoomId;
    private String senderId;
    private String content;
    @CreatedDate
    private LocalDateTime sendDate;
    private int readCount;
    private MessageType messageType; // 채팅 타입 필드 추가('TEXT', 'IMAGE')
    private String imgUrl;

    public static ChatMessage of(int chatRoomId, ChatMessageRequestDTO requestDTO){
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .content(requestDTO.getContent())
                .senderId(requestDTO.getSenderId())
                .messageType(requestDTO.getMessageType())
                .readCount(1)
                .build();
        return chatMessage;
    }
}
