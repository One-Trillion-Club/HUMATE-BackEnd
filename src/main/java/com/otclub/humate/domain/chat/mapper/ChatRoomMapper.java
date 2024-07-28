package com.otclub.humate.domain.chat.mapper;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatRoomMapper {
    int insertChatRoom(ChatRoom chatRoom);
    int insertChatParticipant(ChatParticipate chatParticipate);


}
