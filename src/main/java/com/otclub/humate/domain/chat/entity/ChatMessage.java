package com.otclub.humate.domain.chat.entity;

import com.otclub.humate.domain.chat.dto.ChatType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chatMessage")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage { // implements Persistable<String>

    @Id
    private String _id;
    @Indexed
    private int chatRoomId;
    private String senderId;
    private String content;
    @CreatedDate
    private LocalDateTime sendDate;
    private int readCount;
    private ChatType chatType; // 채팅 타입 필드 추가('TEXT', 'IMAGE')
    private String imgUrl;
}
