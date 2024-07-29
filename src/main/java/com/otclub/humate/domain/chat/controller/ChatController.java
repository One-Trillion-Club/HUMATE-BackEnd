package com.otclub.humate.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    @MessageMapping("/chat/{chatRoomId}")
    @SendTo("/topic/message/{chatRoomId}")
    public String messageHandler(@DestinationVariable("chatRoomId") int chatRoomId, String message){
        log.info("chat controller - messageHandler " + chatRoomId);
        log.info("chat controller - message " + message);

        return message;
    }
}
