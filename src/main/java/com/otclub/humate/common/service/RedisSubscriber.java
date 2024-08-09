package com.otclub.humate.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otclub.humate.domain.chat.dto.MessageWebSocketResponseDTO;
import com.otclub.humate.domain.chat.dto.RoomDetailDTO;
import com.otclub.humate.domain.chat.mapper.RoomMapper;
import com.otclub.humate.domain.chat.vo.Message;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import redis.clients.jedis.Jedis;

/**
 * RedisSubscriber 구독 관련 메세지 전송
 *
 * @author 최유경
 * @since 2024.08.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  	최유경        최초 생성
 * 2024.08.05   최유경        구독한 모두에게 메시지 전송
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final RoomMapper roomMapper;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisWebSocketSessionManager sessionManager;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    /**
     * redis subscribe 처리
     * @param message message must not be {@literal null}.
     * @param pattern pattern matching the channel (if specified) - can be {@literal null}.
     */
    @Override
    public void onMessage(org.springframework.data.redis.connection.Message message, byte[] pattern) {
        try{
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());

            Message chatMessage = objectMapper.readValue(publishMessage, Message.class);

            log.info("[RedisSubscriber] Redis Subscribe Channel : " + chatMessage.getContent());
            log.info("[RedisSubscriber] Redis SUB Message : {}", publishMessage);

            List<String> participateList = roomMapper.selectChatParticipateIdListByParticipateId(chatMessage.getParticipateId());
            // 구독한 모두에게 전송
            for(String p : participateList){
                if(sessionManager.isUserConnected(p)){
                    RoomDetailDTO roomDetailDTO = roomMapper.selectChatRoomDetailDTOByParticipateId(p);
                    MessageWebSocketResponseDTO webSocketResponseDTO = MessageWebSocketResponseDTO.of(roomDetailDTO, chatMessage);
                    String jsonMessage = objectMapper.writeValueAsString(webSocketResponseDTO);
                    sendMessage(p,jsonMessage);
                }
            }
        }
        catch (JsonProcessingException e){
            log.error(e.getMessage());
        }
    }

    /**
     * 메세지 전송
     *
     * @param participateId 참여자 ID
     * @param jsonMessage 보낼 메세지 json
     */
    private void sendMessage(String participateId, String jsonMessage){
        WebSocketSession session = sessionManager.getSession(participateId);
        try {
            session.sendMessage(new TextMessage(jsonMessage));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
