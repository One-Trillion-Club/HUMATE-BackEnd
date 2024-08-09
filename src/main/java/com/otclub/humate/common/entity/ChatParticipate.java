package com.otclub.humate.common.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 채팅 참여자 Entity
 * @author 최유경
 * @since 2024.07.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.31  	최유경        최초 생성
 * </pre>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ChatParticipate {
    // 참여자 ID
    private int participateId;
    // 회원 ID
    private String memberId;
    // 채팅방 ID
    private int chatRoomId;
    // 생성 날짜
    private Date createdAt;
    // 나간 날짜
    private Date exitedAt;
    // 메이트 버튼 클릭 여부
    private int isClicked;

    public static ChatParticipate of(ChatRoom chatRoom, String memberId){
        return ChatParticipate.builder()
                .chatRoomId(chatRoom.getChatRoomId())
                .memberId(memberId)
                .build();
    }
}
