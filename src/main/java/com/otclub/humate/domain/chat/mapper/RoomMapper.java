package com.otclub.humate.domain.chat.mapper;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
import com.otclub.humate.domain.chat.dto.RoomDetailDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 채팅방 Mapper
 * @author 최유경
 * @since 2024.07.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03   최유경        회원 참여 확인 메서드
 * 2024.07.31  	최유경        최초 생성
 * </pre>
 */
@Mapper
public interface RoomMapper {
    // 채팅방 생성
    int insertChatRoom(ChatRoom chatRoom);
    // 채팅방 참여
    int insertChatParticipant(ChatParticipate chatParticipate);
    // 참여 내역 조회
    Optional<ChatParticipate> selectChatParticipantByParticipateId(
            @Param("participateId") String participateId);

    // 채팅방 내역 조회
    List<RoomDetailDTO> selectChatRoomDetailDTOListByMemberId(
            @Param("memberId") String memberId,
            @Param("isMatched") int isMatched);

    // 나랑 같은 채팅방에 참여한 참여자 ID 목록 조회
    List<String> selectChatParticipateIdListByParticipateId(
            @Param("participateId") String participateId);

    // 참여 ID로 채팅방 ID 조회
    Optional<Integer> selectChatRoomIdByParticipateId(
            @Param("participateId") String participateId);

    // 채팅방 ID로 회원 ID 목록 조회
    List<String> selectChatRoomMemberByChatRoomId(int chatRoomId);

    // 채팅방의 메이트 맺기 버튼 클릭 총 횟수 조회
    int selectParticipatesClickCount(@Param("chatRoomId") String chatRoomId);

    // 메이트 매칭 여부 업데이트
    int updateChatRoomStatus(@Param("chatRoomId")String chatRoomId);

    // 참여 ID로 채팅방 상세 DTO 조회
    RoomDetailDTO selectChatRoomDetailDTOByParticipateId(@Param("participateId") String participateId);
}
