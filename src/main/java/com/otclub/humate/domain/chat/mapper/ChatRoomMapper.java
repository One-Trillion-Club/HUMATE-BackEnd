package com.otclub.humate.domain.chat.mapper;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

/**
 * 채팅방 Mapper
 * @author 최유경
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	최유경        최초 생성
 * </pre>
 */
@Mapper
public interface ChatRoomMapper {
    int insertChatRoom(ChatRoom chatRoom);
    int insertChatParticipant(ChatParticipate chatParticipate);


}
