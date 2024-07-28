package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import com.otclub.humate.domain.chat.mapper.ChatRoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService{
    private final ChatRoomMapper chatRoomMapper;
    @Override
    public int createChatRoom(ChatRoomCreateRequestDTO requestDTO) {
        // postId가 유효한지 확인 후, 정보 가져오기


        // 채팅방 생성하기
        ChatRoom chatRoom = createRoom(requestDTO.getPostId());
        chatRoomMapper.insertChatRoom(chatRoom);

        // 채팅방 참여 유저 설정하기
        ChatParticipate applicant = createParticipant(chatRoom, requestDTO.getApplicantId());
        ChatParticipate writer = createParticipant(chatRoom, requestDTO.getWriterId());

        // 채팅방에 참여시키기
        chatRoomMapper.insertChatParticipant(applicant);
        chatRoomMapper.insertChatParticipant(writer);

        return chatRoom.getChatRoomId();
    }

    @Override
    public void joinChatRoom() {

    }

    private ChatParticipate createParticipant(ChatRoom chatRoom, String memberId){
        return ChatParticipate.builder()
                .chatRoomId(chatRoom.getChatRoomId())
                .memberId(memberId)
                .build();
    }

    private ChatRoom createRoom(int postId){
        return ChatRoom.builder()
                .postId(postId)
                .build();
    }


}
