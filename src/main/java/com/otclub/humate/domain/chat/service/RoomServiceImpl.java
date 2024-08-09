package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
import com.otclub.humate.common.entity.Post;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.dto.RoomCreateRequestDTO;
import com.otclub.humate.domain.chat.dto.RoomCreateResponseDTO;
import com.otclub.humate.domain.chat.dto.RoomDetailDTO;
import com.otclub.humate.domain.chat.mapper.RoomMapper;
import com.otclub.humate.domain.mate.mapper.PostMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 채팅방 서비스 구현체
 * @author 최유경
 * @since 2024.07.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.31  	최유경        최초 생성
 * 2024.07.31   김지현        관련 매칭글 정보 저장
 * 2024.08.03   최유경        채팅방 리스트 조회
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomMapper roomMapper;
    private final PostMapper postMapper;

    /**
     * 채팅방 생성
     *
     * @author 최유경
     * @param memberId 요청 회원 ID
     * @param requestDTO 요청 글 정보
     * @return RoomCreateResponseDTO 채팅방 생성 응답
     * @exception CustomException POST_NOT_FOUND
     */
    @Override
    @Transactional
    public RoomCreateResponseDTO createChatRoom(String memberId, RoomCreateRequestDTO requestDTO) {
        // postId가 유효한지 확인 후, 정보 가져오기
        int postId = requestDTO.getPostId();
        log.info("[createChatRoom] - {}", postId);
        if (!postMapper.selectPostCountById(postId)) {
            throw new CustomException(ErrorCode.POST_NOT_FOUND);
        }
        Post post = postMapper.selectPostByPostId(postId);

        // 채팅방 생성하기
        ChatRoom chatRoom = ChatRoom.from(post);
        roomMapper.insertChatRoom(chatRoom);

        log.info("[createChatRoom] - {}", chatRoom.toString());
        // 채팅방 참여 유저 설정하기
        ChatParticipate applicant = ChatParticipate.of(chatRoom, memberId);
        ChatParticipate writer = ChatParticipate.of(chatRoom, post.getMemberId());

        // 채팅방에 참여시키기
        roomMapper.insertChatParticipant(applicant);
        roomMapper.insertChatParticipant(writer);

        return RoomCreateResponseDTO.of(chatRoom.getChatRoomId(),applicant.getParticipateId());
    }

    /**
     * 메이트 채팅/대기 목록 조회
     *
     * @author 최유경
     * @param memberId 요청 회원 ID
     * @param isMatched 상태값 (0-매칭안됨, 1-매칭됨)
     * @return List 메이트 채팅/대기 목록 리스트
     */
    @Override
    public List<RoomDetailDTO> findChatRoomDetailList(String memberId, int isMatched) {
        List<RoomDetailDTO> roomList = roomMapper.selectChatRoomDetailDTOListByMemberId(memberId, isMatched);
        return roomList;
    }

    /**
     * 채팅방 상세 내용 조회
     *
     * @author 최유경
     * @param participateId 참여 회원 ID
     * @return RoomDetailDTO 상세 DTO
     */
    @Override
    public RoomDetailDTO findChatRoomDetail(String participateId) {
        RoomDetailDTO detailDTO = roomMapper.selectChatRoomDetailDTOByParticipateId(participateId);

        return detailDTO;
    }
}
