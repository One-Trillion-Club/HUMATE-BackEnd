package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.RoomCreateRequestDTO;
import com.otclub.humate.domain.chat.dto.RoomCreateResponseDTO;
import com.otclub.humate.domain.chat.dto.RoomDetailDTO;
import java.util.List;

/**
 * 채팅방 서비스
 * @author 최유경
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03   최유경        채팅방 리스트 조회
 * 2024.07.28  	최유경        최초 생성
 * </pre>
 */
public interface RoomService {
    RoomCreateResponseDTO createChatRoom(String memberId, RoomCreateRequestDTO requestDTO); // 채팅방 생성
    List<RoomDetailDTO> findChatRoomList(String memberId, int isMatched);
}
