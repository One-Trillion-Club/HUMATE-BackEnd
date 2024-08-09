package com.otclub.humate.domain.chat.vo;

import com.otclub.humate.domain.chat.dto.MessageRedisDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 채팅메세지 MongoDB Document
 * @author 최유경
 * @since 2024.08.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.01   	최유경        최초 생성
 * </pre>
 */

@Document(collection = "chatMessage")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Message {

    @Id
    // mongoDB 고유 ID
    private String _id;
    @Indexed
    // 채팅방 ID
    private String chatRoomId;
    // 참여 ID
    private String participateId;
    // 채팅 내용
    private String content;
    // 보낸 날짜
    private String createdAt;
    // 읽음 여부
    private int readCount;
    // 채팅 유형
    private MessageType messageType; // 채팅 타입 필드 추가('TEXT', 'IMAGE')
    // 채팅 이미지 내용
    private String imgUrl;

    public static Message of(MessageRedisDTO requestDTO){
        Message message = Message.builder()
                .chatRoomId(requestDTO.getChatRoomId())
                .participateId(requestDTO.getParticipateId())
                .content(requestDTO.getContent())
                .createdAt(getDate())
                .readCount(1)
                .messageType(requestDTO.getMessageType())
                .build();
        return message;
    }

    private static String getDate() {
        Date date = new Date();

        // 날짜 형식을 지정 (예: "hh:mm" - 12시간 형식)
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = timeFormat.format(date);

        return formattedTime;
    }
}
