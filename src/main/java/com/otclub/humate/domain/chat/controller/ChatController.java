package com.otclub.humate.domain.chat.controller;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import com.otclub.humate.domain.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    @Autowired
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/{chatRoomId}")
    @SendTo("/topic/message/{chatRoomId}")
    public ChatMessage messageHandler(@DestinationVariable("chatRoomId") int chatRoomId, ChatMessageRequestDTO requestDTO){
        log.info("chat controller - messageHandler " + chatRoomId);
        log.info("chat controller - message " + requestDTO.getContent());

        // 채팅 메시지 생성
        ChatMessage chatMessage = chatMessageService.createMessage(chatRoomId, requestDTO);

        return chatMessage;
    }
}
