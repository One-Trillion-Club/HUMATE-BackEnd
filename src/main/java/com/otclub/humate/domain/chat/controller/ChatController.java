package com.otclub.humate.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public String messageHandler(String message){
        log.info("chat controller - message " + message);

        return message;
    }
}
