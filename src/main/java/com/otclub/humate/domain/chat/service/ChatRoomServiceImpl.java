package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import com.otclub.humate.domain.chat.dto.ChatRoomDetailDTO;
import com.otclub.humate.domain.chat.mapper.ChatRoomMapper;
import com.otclub.humate.domain.mate.mapper.PostMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 채팅방 서비스
 * @author 최유경
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03   최유경        채팅방 리스트 조회
 * 2024.07.29   김지현        관련 매칭글 정보 저장
 * 2024.07.28  	최유경        최초 생성
 * </pre>
 */
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
        ChatRoom chatRoom = ChatRoom.from(requestDTO.getPostId());
        chatRoomMapper.insertChatRoom(chatRoom);

        // 채팅방 참여 유저 설정하기
        ChatParticipate applicant = ChatParticipate.of(chatRoom, requestDTO.getApplicantId());
        ChatParticipate writer = ChatParticipate.of(chatRoom, requestDTO.getWriterId());

        // 채팅방에 참여시키기
        chatRoomMapper.insertChatParticipant(applicant);
        chatRoomMapper.insertChatParticipant(writer);

        return chatRoom.getChatRoomId();
    }

    @Override
    public List<ChatRoomDetailDTO> findChatRoomList(String memberId, int isMatched) {
        List<ChatRoomDetailDTO> roomList = chatRoomMapper.selectChatList(memberId, isMatched);
        return roomList;
    }
}
