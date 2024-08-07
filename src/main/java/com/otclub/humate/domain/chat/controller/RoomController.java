package com.otclub.humate.domain.chat.controller;

import com.otclub.humate.common.annotation.MemberId;
import com.otclub.humate.domain.chat.dto.RoomCreateRequestDTO;
import com.otclub.humate.domain.chat.dto.RoomCreateResponseDTO;
import com.otclub.humate.domain.chat.dto.RoomDetailDTO;
import com.otclub.humate.domain.chat.service.RoomService;
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
 * @since 2024.07.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.31  	최유경        최초 생성
 * 2024.08.03  	최유경        채팅방 리스트 내역 조회
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@Slf4j
public class RoomController {
    private final RoomService roomService;

    /**
     * 채팅방 생성
     * @author 최유경
     * @param memberId 회원 ID
     * @return 채팅방 리스트
     */
    @PostMapping("/create")
    public ResponseEntity<RoomCreateResponseDTO> chatRoomCreate(@MemberId String memberId, @RequestBody RoomCreateRequestDTO requestDTO){
        RoomCreateResponseDTO responseDTO = roomService.createChatRoom(memberId, requestDTO);

        return ResponseEntity.ok(responseDTO);
    }

    /**
     * 채팅방 조회
     * @author 최유경
     * @param memberId 회원 ID
     * @return 채팅방 리스트
     */
    @GetMapping("/list")
    public ResponseEntity<List<RoomDetailDTO>> chatRoomList(@MemberId String memberId){
        List<RoomDetailDTO> roomList = roomService.findChatRoomDetailList(memberId, 1);

        return ResponseEntity.ok(roomList);
    }

    /**
     * 대기 채팅방 조회
     * @author 최유경
     * @param memberId 회원 ID
     * @return 채팅방 리스트
     */
    @ResponseBody
    @GetMapping("/list/pending")
    public ResponseEntity<List<RoomDetailDTO>> chatRoomPendingList(@MemberId String memberId){
        List<RoomDetailDTO> roomList = roomService.findChatRoomDetailList(memberId, 0);

        return ResponseEntity.ok(roomList);
    }
}
