package com.otclub.humate.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import com.otclub.humate.domain.chat.vo.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try{
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());

            ChatMessageRequestDTO requestDTO = objectMapper.readValue(publishMessage, ChatMessageRequestDTO.class);

            log.info("[RedisSubscriber] Redis Subscribe Channel : " + requestDTO.getContent());
            log.info("[RedisSubscriber] Redis SUB Message : {}", publishMessage);

            if(requestDTO.getMessageType().equals(MessageType.TEXT)){
                // 채팅방을 구독한 클라이언트에게 메시지 발송
                messagingTemplate.convertAndSend(
                        "/topic/chat/room/" + requestDTO.getChatRoomId(), requestDTO);
            }
        }
        catch (JsonProcessingException e){
            log.error(e.getMessage());
        }
    }
}
