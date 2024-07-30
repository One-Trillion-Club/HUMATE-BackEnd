package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import java.util.List;

public interface ChatMessageService {
    ChatMessage createMessage(int roomId, ChatMessageRequestDTO requestDTO);

    List<ChatMessage> getListMessage(int roomId);
}
