package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.service.RedisPublisher;
import com.otclub.humate.common.service.RedisSubscriber;
import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.dto.MateCreateRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPubSubService {
    private final ChatMessageService chatMessageService;
    private final ChannelTopic topic;
    private final RedisMessageListenerContainer listenerContainer;
    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;

    public void subscribeToChannel(String chatRoomId) {
        // 요청한 Channel 을 구독
        listenerContainer.addMessageListener(redisSubscriber, new ChannelTopic(topic.getTopic() + "/" + chatRoomId));
    }

    public void pubSendMessageChannel(ChatMessageRequestDTO requestDTO){
        // 요청한 Channel 을 구독
        listenerContainer.addMessageListener(redisSubscriber, new ChannelTopic(topic.getTopic() + "/" + requestDTO.getChatRoomId()));
        ChatMessageRedisDTO redisDTO = ChatMessageRedisDTO.from(requestDTO);

        int subscriberCount = redisSubscriber.getSubscriberCount(topic.getTopic() + "/" + requestDTO.getChatRoomId());
        log.info("[pubSendMessageChannel] - subscriberCount : {} ", subscriberCount);
        // 채팅 메시지 저장
        ChatMessage chatMessage = chatMessageService.createMessage(requestDTO.getChatRoomId(), redisDTO);

        //Message 전송
        redisPublisher.publish(redisDTO.getChatRoomId(), redisDTO);
    }


    // 메이트 신청/취소 전송
    public void pubSendMessageChannel(ChatMessageRedisDTO redisDTO){
        // 요청한 Channel 을 구독
        listenerContainer.addMessageListener(redisSubscriber, new ChannelTopic(topic.getTopic() + "/" + redisDTO.getChatRoomId()));

        // 채팅 메시지 저장
        ChatMessage chatMessage = chatMessageService.createMessage(redisDTO.getChatRoomId(), redisDTO);

        //Message 전송
        redisPublisher.publish(redisDTO.getChatRoomId(), redisDTO);
    }

    /**
     * Channel 구독 취소
     * @param channel
     */
    public void cancelSubChannel(String channel) {
        listenerContainer.removeMessageListener(redisSubscriber);
    }
}
