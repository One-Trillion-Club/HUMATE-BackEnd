package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import java.util.List;
import lombok.Builder.Default;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService{
    @Autowired
    private MongoTemplate mongoTemplate;

    private static int pageSize = 5;


    @Override
    public ChatMessage createMessage(int chatRoomId, ChatMessageRequestDTO requestDTO) {
        ChatMessage chatMessage = ChatMessage.of(chatRoomId, requestDTO);

        // 몽고디비 저장하기
        mongoTemplate.insert(chatMessage);

        return chatMessage;
    }

    @Override
    public List<ChatMessage> getListMessage(int chatRoomId) {
        // 목록 조회
        List<ChatMessage> chatMessageList = mongoTemplate.find(
                Query.query(Criteria.where("chatRoomId").is(chatRoomId)),
                ChatMessage.class
        );

        return chatMessageList;
    }
}
