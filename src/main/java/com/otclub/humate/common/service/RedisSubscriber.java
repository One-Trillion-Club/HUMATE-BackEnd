package com.otclub.humate.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.MessageType;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import redis.clients.jedis.Jedis;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisWebSocketSessionManager sessionManager;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    public int getSubscriberCount(String channel) {
        try (Jedis jedis = new Jedis(redisHost, redisPort)) {
            return jedis.pubsubNumSub(channel).get(channel).intValue();
        } catch (Exception e) {
            log.error("Failed to get subscriber count: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try{
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());

            ChatMessageRequestDTO requestDTO = objectMapper.readValue(publishMessage, ChatMessageRequestDTO.class);

            log.info("[RedisSubscriber] Redis Subscribe Channel : " + requestDTO.getContent());
            log.info("[RedisSubscriber] Redis SUB Message : {}", publishMessage);

            //if(requestDTO.getMessageType().equals(MessageType.TEXT)) {
                //if (sessionManager.isUserConnected(requestDTO.getSenderId())) {
                if (requestDTO.getSenderId().equals("F_1")) {
                    WebSocketSession session = sessionManager.getSession("K_1");
                    log.info("[RedisSubscriber session : {}", session.toString());

                    String jsonMessage = objectMapper.writeValueAsString(requestDTO);
                    log.info("[RedisSubscriber] jsonMessage : {}", jsonMessage);
                    try {
                        session.sendMessage(new TextMessage(jsonMessage));
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }
           // }
        }
        catch (JsonProcessingException e){
            log.error(e.getMessage());
        }
    }
}
