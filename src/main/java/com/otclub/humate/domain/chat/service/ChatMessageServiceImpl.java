package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{
    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public ChatMessage createMessage(int chatRoomId, ChatMessageRequestDTO requestDTO) {
        ChatMessage chatMessage = ChatMessage.of(chatRoomId, requestDTO);

        // 몽고디비 저장하기
        mongoTemplate.insert(chatMessage);

        return chatMessage;
    }

}
