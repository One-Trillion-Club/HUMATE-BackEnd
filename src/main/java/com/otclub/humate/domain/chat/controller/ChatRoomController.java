package com.otclub.humate.domain.chat.controller;

import com.otclub.humate.common.annotation.MemberId;
import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import com.otclub.humate.domain.chat.dto.ChatRoomCreateResponseDTO;
import com.otclub.humate.domain.chat.dto.ChatRoomDetailResponseDTO;
import com.otclub.humate.domain.chat.service.ChatRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
 * 2024.08.03  	최유경        채팅방 리스트 내역 조회
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
    public ResponseEntity<ChatRoomCreateResponseDTO> chatRoomCreate(@MemberId String memberId, @RequestBody ChatRoomCreateRequestDTO requestDTO){
        ChatRoomCreateResponseDTO responseDTO = chatRoomService.createChatRoom(memberId, requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @ResponseBody
    @GetMapping("/list")
    public ResponseEntity<List<ChatRoomDetailResponseDTO>> chatRoomList(@MemberId String memberId){
        List<ChatRoomDetailResponseDTO> roomList = chatRoomService.findChatRoomList(memberId, 1);

        return ResponseEntity.ok(roomList);
    }

    @ResponseBody
    @GetMapping("/list/pending")
    public ResponseEntity<List<ChatRoomDetailResponseDTO>> ㄲchatRoomPendingList(@MemberId String memberId){
        List<ChatRoomDetailResponseDTO> roomList = chatRoomService.findChatRoomList(memberId, 0);

        return ResponseEntity.ok(roomList);
    }
}
