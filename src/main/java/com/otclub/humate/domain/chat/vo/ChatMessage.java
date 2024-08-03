package com.otclub.humate.domain.chat.vo;

import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@ToString
public class ChatMessage {

    @Id
    private String _id;
    @Indexed
    private String chatRoomId;
    private String senderId;
    private String content;
    private Date createdAt;
    private int readCount;
    private MessageType messageType; // 채팅 타입 필드 추가('TEXT', 'IMAGE')
    private String imgUrl;

    public static ChatMessage of(String chatRoomId, ChatMessageRedisDTO requestDTO){
        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(requestDTO.getSenderId())
                .content(requestDTO.getContent())
                .createdAt(getDate())
                .readCount(1)
                .messageType(requestDTO.getMessageType())
                .build();
        return chatMessage;
    }

    private static Date getDate(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 9);

        return new Date(calendar.getTimeInMillis());
    }
}
