package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;

public interface ChatMessageService {
    ChatMessage createMessage(int roomId, ChatMessageRequestDTO requestDTO);
}
