package com.otclub.humate.common.service;

import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.mapper.ChatRoomMapper;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

/**
 * Redis pub/sub 서비스
 * @author 최유경
 * @since 2024.07.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  	최유경        메이트 신청/취소 공지 전송
 * 2024.07.31  	최유경        최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPubSubService {
    @Value("${session.prefix.channel")
    private String CHANNEL_PREFIX;
    private final ChatRoomMapper chatRoomMapper;

    private final RedisMessageListenerContainer listenerContainer;
    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;

    public void subscribe(String participateId) {
        log.info("[RedisPubSubService] subscribe {} ", participateId);
        Integer channel = chatRoomMapper.selectChatRoomIdByParticipateId(participateId)
                .orElseThrow(()->new CustomException(ErrorCode.CHAT_PARTICIPATE_NOT_FOUND));

        // 요청한 Channel 을 구독
        listenerContainer.addMessageListener(redisSubscriber, ChannelTopic.of(getChannelName(String.valueOf(channel))));
    }

    // 이벤트 발행
    public void publish(String channel, ChatMessage message) {
        redisPublisher.publish(getChannelName(channel), message);
    }

    /**
     * Channel 구독 취소
     * @param participateId
     */
    public void cancelSubChannel(String participateId) {
        Integer channel = chatRoomMapper.selectChatRoomIdByParticipateId(participateId)
                .orElseThrow(()->new CustomException(ErrorCode.CHAT_PARTICIPATE_NOT_FOUND));
        listenerContainer.removeMessageListener(redisSubscriber, ChannelTopic.of(getChannelName(String.valueOf(channel))));
    }

    private String getChannelName(String id) {
        return CHANNEL_PREFIX  + id;
    }
}
