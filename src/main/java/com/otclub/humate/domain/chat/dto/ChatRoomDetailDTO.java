package com.otclub.humate.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 채팅방 상세 DTO
 * @author 최유경
 * @since 2024.08.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03   최유경        최초 생성
 * </pre>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatRoomDetailDTO {
    private int chatRoomId;
    private String memberId;
    private int participateId;
    private int postId;
    private String postTitle;
    private String matchDate;
    private String matchBranch;
    private int isClicked;
    private int isMatched;
//    private String latestContent; // 마지막 메시지
//    private String latestContentTime; // 마지막 메시지 전송 시간
    private String targetNickname; // 상대방 닉네임
    private String targetMemberId;
    private String targetParticipateId;
    private String targetProfileImgUrl; // 상대방 프로필
    private int targetIsClicked; // 상대방 메이트 맺기 클릭 여부
}

