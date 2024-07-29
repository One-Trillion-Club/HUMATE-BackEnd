package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.entity.ChatMessage;

public interface ChatMessageService {
    ChatMessage createMessage(int roomId, ChatMessageRequestDTO requestDTO);
}
