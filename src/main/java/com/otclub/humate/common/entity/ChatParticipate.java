package com.otclub.humate.common.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 참여 Entity
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatParticipate {
    private int participantId;
    private String memberId;
    private int chatRoomId;
    private Date createdAt;
    private Date exitedAt;
    private int isClicked;

    public static ChatParticipate of(ChatRoom chatRoom, String memberId){
        return ChatParticipate.builder()
                .chatRoomId(chatRoom.getChatRoomId())
                .memberId(memberId)
                .build();
    }
}
