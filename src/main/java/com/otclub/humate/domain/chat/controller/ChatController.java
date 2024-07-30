package com.otclub.humate.domain.chat.controller;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import com.otclub.humate.domain.chat.service.ChatMessageService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{chatRoomId}")
    @SendTo("/topic/message/{chatRoomId}")
    public void messageHandler(@DestinationVariable("chatRoomId") int chatRoomId, ChatMessageRequestDTO requestDTO){
        log.info("chat controller - messageHandler " + chatRoomId);
        log.info("chat controller - message " + requestDTO.getContent());

        // 채팅 메시지 생성
        ChatMessage chatMessage = chatMessageService.createMessage(chatRoomId, requestDTO);

        simpMessagingTemplate.convertAndSend("/topic/message/"+chatRoomId, chatMessage);
    }

    @GetMapping("/chat/{chatRoomId}")
    public ResponseEntity<List<ChatMessage>> chatMessageList(@PathVariable("chatRoomId") int chatRoomId){
        List<ChatMessage> chatMessageList = chatMessageService.getListMessage(chatRoomId);

        return ResponseEntity.ok(chatMessageList);
    }
}
