package com.otclub.humate.domain.chat.controller;

import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import com.otclub.humate.domain.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 채팅방 컨트롤러
 * @author 최유경
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	최유경        최초 생성
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@Slf4j
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @ResponseBody
    @PostMapping("/create")
    public ResponseEntity<Integer> chatRoomCreate(@RequestBody ChatRoomCreateRequestDTO requestDTO){
        Integer chatRoomId = chatRoomService.createChatRoom(requestDTO);

        return ResponseEntity.ok(chatRoomId);
    }

}
