package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.common.entity.ChatRoom;
import com.otclub.humate.common.entity.Member;
import com.otclub.humate.domain.chat.vo.MessageType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessageRedisDTO {
    private String chatRoomId;
    private String senderId;
    private String content;
    private MessageType messageType;

    public static ChatMessageRedisDTO from(ChatMessageRequestDTO requestDTO){
        return ChatMessageRedisDTO.builder()
                .chatRoomId(requestDTO.getChatRoomId())
                .senderId(requestDTO.getSenderId())
                .content(requestDTO.getContent())
                .messageType(requestDTO.getMessageType())
                .build();
    }

    public static ChatMessageRedisDTO ofMateActive(MateCreateRequestDTO requestDTO, String nickname){
        MessageType messageType = (requestDTO.getIsClicked()==1 ? MessageType.MATE_ACTIVE : MessageType.MATE_INACTIVE);

        return ChatMessageRedisDTO.builder()
                .chatRoomId(requestDTO.getChatRoomId())
                .senderId(requestDTO.getMemberId())
                .content(nickname + messageType.getMsg())
                .messageType(messageType.MATE_ACTIVE)
                .build();
    }
}
