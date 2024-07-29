package com.otclub.humate.domain.chat.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatMessageRequestDTO {
    private String senderId;
    private String content;
    private ChatType chatType;
}
