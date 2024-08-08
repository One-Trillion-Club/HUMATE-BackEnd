package com.otclub.humate.common.service;

import com.otclub.humate.domain.chat.dto.MessageRedisDTO;
import com.otclub.humate.domain.chat.vo.Message;

public interface MessagePublisher {
    void publish(String channel, String message);
    void publish(String channel, MessageRedisDTO message);
    void publish(String channel, Message message);
}
