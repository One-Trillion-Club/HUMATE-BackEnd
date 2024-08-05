package com.otclub.humate.common.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPublisher implements MessagePublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void publish(String channel, String data){
        log.info("[RedisPublisher] - publish topic full path : {} ", channel);
        redisTemplate.convertAndSend(channel, data);
    }

    @Override
    public void publish(String channel, ChatMessageRedisDTO dto){
        log.info("[RedisPublisher] - publish topic full path : {} ", channel);
        redisTemplate.convertAndSend(channel, dto);
    }

    @Override
    public void publish(String channel, ChatMessage dto){
        log.info("[RedisPublisher] - publish topic full path : {} ", channel);
        redisTemplate.convertAndSend(channel, dto);
    }


}
