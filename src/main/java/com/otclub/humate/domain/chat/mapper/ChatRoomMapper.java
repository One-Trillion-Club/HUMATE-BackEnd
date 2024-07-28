package com.otclub.humate.domain.chat.mapper;

import com.otclub.humate.domain.chat.domain.ChatRoomVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatRoomMapper {
    int insertChatRoom(ChatRoomVO chatRoomVO);



}
