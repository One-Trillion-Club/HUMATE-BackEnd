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

/**
 * 채팅 메세지 컨트롤러
 * @author 최유경
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30   최유경        채팅 조회
 * 2024.07.29   최유경        MongoDB 연결
 * 2024.07.28  	최유경        최초 생성
 * </pre>
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatMessageService chatMessageService;

    @GetMapping("/chat/{chatRoomId}")
    public ResponseEntity<List<ChatMessage>> chatHistoryList(@PathVariable("chatRoomId") String chatRoomId){
        log.info("[채팅내역조회] - {}", chatRoomId);
        List<ChatMessage> chatMessageList = chatMessageService.getListMessage(chatRoomId);

        return ResponseEntity.ok(chatMessageList);
    }

    @GetMapping("/chat/history/{chatRoomId}")
    public ResponseEntity<List<ChatMessage>> chatMessageHistoryList(@PathVariable("chatRoomId") String chatRoomId){
        log.info("[채팅내역조회] - {}", chatRoomId);
        List<ChatMessage> chatMessageList = chatMessageService.getListMessage(chatRoomId);

        return ResponseEntity.ok(chatMessageList);
    }
}
