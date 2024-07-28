package com.otclub.humate.domain.chat.service;

import org.springframework.stereotype.Service;

public interface ChatRoomService {
    int createChatRoom(); // 채팅방 생성
    void joinChatRoom(); // 채팅방 입장
}
