package com.otclub.humate.common.service;

import com.otclub.humate.domain.chat.dto.MessageRedisDTO;
import com.otclub.humate.domain.chat.vo.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


/**
 * MessagePublisher
 *
 * @author 최유경
 * @since 2024.08.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.02  	최유경        최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPublisher implements MessagePublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis 메시지 발행
     *
     * @author 최유경
     * @param channel 구독한 채널
     * @param dto 보낼 메세지
     */
    @Override
    public void publish(String channel, Message dto){
        log.info("[RedisPublisher] - publish topic full path : {} ", channel);
        redisTemplate.convertAndSend(channel, dto);
    }


}
