package com.otclub.humate.domain.chat.mapper;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
import com.otclub.humate.domain.chat.dto.ChatRoomDetailDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 채팅방 Mapper
 * @author 최유경
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03   최유경        회원 참여 확인 메서드
 * 2024.07.28  	최유경        최초 생성
 * </pre>
 */
@Mapper
public interface ChatRoomMapper {
    int insertChatRoom(ChatRoom chatRoom);
    int insertChatParticipant(ChatParticipate chatParticipate);
    Optional<ChatParticipate> selectChatParticipantByParticipateId(
            @Param("participateId") String participateId);

    List<ChatRoomDetailDTO> selectChatList(
            @Param("memberId") String memberId,
            @Param("isMatched") int isMatched);

    List<String> selectChatParticipateIdListByParticipateId(
            @Param("participateId") String participateId);

    Optional<Integer> selectChatRoomIdByParticipateId(
            @Param("participateId") String participateId);

}
