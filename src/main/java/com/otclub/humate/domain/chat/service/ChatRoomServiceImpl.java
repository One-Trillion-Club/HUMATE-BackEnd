package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import com.otclub.humate.domain.chat.mapper.ChatRoomMapper;
import com.otclub.humate.domain.mate.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{
    private final ChatRoomMapper chatRoomMapper;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public int createChatRoom(ChatRoomCreateRequestDTO requestDTO) {
        // postId가 유효한지 확인 후, 정보 가져오기
        int postId = requestDTO.getPostId();
        if (!postMapper.selectPostCountById(postId)) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }

        // 채팅방 생성하기
        ChatRoom chatRoom = createRoom(postId);
        chatRoomMapper.insertChatRoom(chatRoom);

        // 채팅방 참여 유저 설정하기
        ChatParticipate applicant = createParticipant(chatRoom, requestDTO.getApplicantId());
        ChatParticipate writer = createParticipant(chatRoom, requestDTO.getWriterId());

        // 채팅방에 참여시키기
        chatRoomMapper.insertChatParticipant(applicant);
        chatRoomMapper.insertChatParticipant(writer);

        return chatRoom.getChatRoomId();
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
