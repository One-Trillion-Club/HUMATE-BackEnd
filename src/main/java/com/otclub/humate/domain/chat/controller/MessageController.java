package com.otclub.humate.domain.chat.controller;

import com.otclub.humate.domain.chat.dto.RoomDetailDTO;
import com.otclub.humate.domain.chat.service.RoomService;
import com.otclub.humate.domain.chat.vo.Message;
import com.otclub.humate.domain.chat.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/chat")
@Slf4j
public class MessageController {
    private final MessageService messageService;
    private final RoomService roomService;

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<Message>> chatHistoryList(@PathVariable("chatRoomId") String chatRoomId){
        log.info("[채팅내역조회] - {}", chatRoomId);
        List<Message> messageList = messageService.getListMessage(chatRoomId);

        return ResponseEntity.ok(messageList);
    }

    @GetMapping("/history/{participateId}")
    public ResponseEntity<List<Message>> chatMessageHistoryList(@PathVariable("participateId") String participateId){
        log.info("[채팅내역조회] - {}", participateId);
        RoomDetailDTO detailDTO = roomService.findChatRoomDetail(participateId);
        List<Message> messageList = messageService.getListMessage(String.valueOf(detailDTO.getChatRoomId()));

        return ResponseEntity.ok(messageList);
    }
}
