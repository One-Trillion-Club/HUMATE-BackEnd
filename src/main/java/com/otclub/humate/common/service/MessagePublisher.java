package com.otclub.humate.common.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;

public interface MessagePublisher {
    void publish(String chatRoomId, String message);
    void publish(String chatRoomId, ChatMessageRequestDTO message);
}
