package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * 채팅 메세지 서비스
 * @author 최유경
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30   최유경        채팅메세지 내역 조회
 * 2024.07.29  	최유경        최초 생성
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService{
    private final MongoTemplate mongoTemplate;

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
