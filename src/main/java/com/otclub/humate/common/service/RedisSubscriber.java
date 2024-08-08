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

    public int getSubscriberCount(String channel) {
        try (Jedis jedis = new Jedis(redisHost, redisPort)) {
            return jedis.pubsubNumSub(channel).get(channel).intValue();
        } catch (Exception e) {
            log.error("Failed to get subscriber count: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public void onMessage(org.springframework.data.redis.connection.Message message, byte[] pattern) {
        try{
            String publishMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());

            Message chatMessage = objectMapper.readValue(publishMessage, Message.class);

            log.info("[RedisSubscriber] Redis Subscribe Channel : " + chatMessage.getContent());
            log.info("[RedisSubscriber] Redis SUB Message : {}", publishMessage);

            List<String> participateList = roomMapper.selectChatParticipateIdListByParticipateId(chatMessage.getParticipateId());
            log.info("[RedisSubscriber] participateList size : {}", participateList.size());
            // 공지 관련 글 (입장, 퇴장, 메이트 신청, 메이트 취소)은 구독한 모두에게 전송
            for(String p : participateList){
                if(sessionManager.isUserConnected(p)){
                    RoomDetailDTO roomDetailDTO = roomMapper.selectChatRoomDetailDTOByParticipateId(p);
                    MessageWebSocketResponseDTO webSocketResponseDTO = MessageWebSocketResponseDTO.of(roomDetailDTO, chatMessage);
                    String jsonMessage = objectMapper.writeValueAsString(webSocketResponseDTO);
                    sendMessage(p,jsonMessage);
                }
            }

//            if(isNotice(chatMessage.getMessageType())){
//                for(String p : participateList){
//                    if(sessionManager.isUserConnected(p)){
//                        ChatRoomDetailDTO chatRoomDetailDTO = chatRoomMapper.selectChatRoomDetailDTOByParticipateId(p);
//                        ChatMessageWebSocketResponseDTO webSocketResponseDTO = ChatMessageWebSocketResponseDTO.of(chatRoomDetailDTO, chatMessage);
//                        String jsonMessage = objectMapper.writeValueAsString(webSocketResponseDTO);
//                        sendMessage(p,jsonMessage);
//                    }
//                }
//            }
//            // 채팅 글 (텍스트, 이미지)은 나를 제외한 구독한 모두에게 전송
//            else {
//                for(String p : participateList){
//                    log.info("[RedisSubscriber] participateList {}", p);
//                        if(sessionManager.isUserConnected(p)){
//                            ChatRoomDetailDTO chatRoomDetailDTO = chatRoomMapper.selectChatRoomDetailDTOByParticipateId(p);
//                            ChatMessageWebSocketResponseDTO webSocketResponseDTO = ChatMessageWebSocketResponseDTO.of(chatRoomDetailDTO, chatMessage);
//                            String jsonMessage = objectMapper.writeValueAsString(webSocketResponseDTO);
//                            sendMessage(p,jsonMessage);
//                        }
//                }
//            }
        }
        catch (JsonProcessingException e){
            log.error(e.getMessage());
        }
    }

//    private boolean isNotice(MessageType messageType){
//        if(MessageType.TEXT.equals(messageType) || MessageType.IMAGE.equals(messageType))
//            return false;
//        return true;
//    }

    private void sendMessage(String participateId, String jsonMessage){
        WebSocketSession session = sessionManager.getSession(participateId);
        log.info("[RedisSubscriber session : {}", session.toString());

        log.info("[RedisSubscriber] jsonMessage : {}", jsonMessage);
        try {
            session.sendMessage(new TextMessage(jsonMessage));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
