package com.otclub.humate.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otclub.humate.common.service.RedisWebSocketSessionManager;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.common.service.RedisPubSubService;
import com.otclub.humate.domain.chat.service.ChatMessageService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    private final ChatMessageService chatMessageService;
    private final RedisPubSubService redisPubSubService;
    private final RedisWebSocketSessionManager sessionManager;
    private final ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("[afterConnectionEstablished] session : {}", session.toString());
        String participateId = extractMemberId(session);
        log.info("[afterConnectionEstablished] participateId : {} ", participateId);
        sessionManager.addSession(participateId, session);
        redisPubSubService.subscribe(participateId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("[afterConnectionClosed] session : {}", session.toString());
        log.info("[afterConnectionClosed] CloseStatus {} ", status.toString());
        String participateId = extractMemberId(session);
        log.info("[afterConnectionClosed] participateId : {} ", participateId);
        sessionManager.removeSession(participateId);
        redisPubSubService.cancelSubChannel(participateId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String id = session.getId();
        String payload = message.getPayload();
        log.info("[WebSocketHandler] - handleTextMessage id : {}, payload : {}" , id, payload);

        ChatMessageRequestDTO requestDTO = objectMapper.readValue(payload, ChatMessageRequestDTO.class);
        log.info("[WebSocketHandler] - requestDTO {}" , requestDTO.toString());

        chatMessageService.createMessage(requestDTO); // 메시지를 Redis 에 publish
    }

    private String extractMemberId(WebSocketSession session){
        Map<String, List<String>> headers = session.getHandshakeHeaders();
        List<String> authorizationHeaders = headers.get("Authorization");
        log.info("[getHandshakeHeaders] - authorization : {}",  headers.get("Authorization").toString());

        if(authorizationHeaders != null && !authorizationHeaders.isEmpty()){
            log.info("[getHandshakeHeaders] - authorization : {}", authorizationHeaders.get(0));
            return authorizationHeaders.get(0);
        }
        return null;
    }
}
