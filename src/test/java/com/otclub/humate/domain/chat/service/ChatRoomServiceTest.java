package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatRoomCreateRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatRoomServiceTest {
    @Autowired ChatRoomService chatRoomService;

    @Test
    public void 채팅방생성(){
        ChatRoomCreateRequestDTO requestDTO
                = ChatRoomCreateRequestDTO.builder()
                        .postId(1)
                                .writerId("K_1")
                                        .applicantId("F_1")
                                                .build();

        chatRoomService.createChatRoom(requestDTO);
    }

}
