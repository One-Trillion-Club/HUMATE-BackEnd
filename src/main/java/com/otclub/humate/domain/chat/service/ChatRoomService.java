package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import com.otclub.humate.domain.chat.dto.ChatRoomDetailDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 채팅방 서비스
 * @author 최유경
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	최유경        최초 생성
 * </pre>
 */
public interface ChatRoomService {
    int createChatRoom(ChatRoomCreateRequestDTO requestDTO); // 채팅방 생성
    List<ChatRoomDetailDTO> findChatRoomList(String memberId, int isMatched);
}
