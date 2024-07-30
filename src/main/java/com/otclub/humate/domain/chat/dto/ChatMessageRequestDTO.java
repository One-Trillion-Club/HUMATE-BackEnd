package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatMessageRequestDTO {
    private String senderId;
    private String content;
    private MessageType messageType;
}
