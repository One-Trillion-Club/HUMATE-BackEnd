package com.otclub.humate.common.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPublisher implements MessagePublisher {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ChannelTopic topic;

    @Override
    public void publish(String chatRoomId, String data){
        log.info("[RedisPublisher] - publish topic : {} ", topic.getTopic());
        log.info("[RedisPublisher] - publish topic full path : {} ", topic.getTopic() + "/" + chatRoomId);
        redisTemplate.convertAndSend(topic.getTopic() + "/" + chatRoomId, data);
    }

    @Override
    public void publish(String chatRoomId, ChatMessageRequestDTO dto){
        log.info("[RedisPublisher] - publish topic : {} ", topic.getTopic());
        log.info("[RedisPublisher] - publish topic full path : {} ", topic.getTopic() + "/" + chatRoomId);
        redisTemplate.convertAndSend(topic.getTopic() + "/" + chatRoomId, dto);
    }


}
