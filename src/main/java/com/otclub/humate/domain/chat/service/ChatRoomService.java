package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import org.springframework.stereotype.Service;

public interface ChatRoomService {
    int createChatRoom(ChatRoomCreateRequestDTO requestDTO); // 채팅방 생성
}
