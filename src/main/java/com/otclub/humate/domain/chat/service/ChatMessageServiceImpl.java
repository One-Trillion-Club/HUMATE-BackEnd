package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.service.RedisPubSubService;
import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final RedisPubSubService redisPubSubService;

    @Override
    public void createMessage(ChatMessageRequestDTO requestDTO) {
        ChatMessageRedisDTO redisDTO = ChatMessageRedisDTO.from(requestDTO);
        sendMessage(redisDTO);
    }


    @Override
    public void createMessage(ChatMessageRedisDTO redisDTO) {
        sendMessage(redisDTO);
    }

    @Override
    public List<ChatMessage> getListMessage(String chatRoomId) {
        // 목록 조회
        List<ChatMessage> chatMessageList = mongoTemplate.find(
                Query.query(Criteria.where("chatRoomId").is(chatRoomId)),
                ChatMessage.class
        );

        return chatMessageList;
    }

    private void sendMessage(ChatMessageRedisDTO redisDTO){
        ChatMessage chatMessage = ChatMessage.of(redisDTO);

        // 몽고디비 저장하기
        mongoTemplate.insert(chatMessage);

        //Message 전송
        redisPubSubService.publish(chatMessage.getChatRoomId(), chatMessage);
    }
}
