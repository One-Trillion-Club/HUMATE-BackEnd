package com.otclub.humate.common.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;

public interface MessagePublisher {
    void publish(String channel, String message);
    void publish(String channel, ChatMessageRedisDTO message);
    void publish(String channel, ChatMessage message);
}
