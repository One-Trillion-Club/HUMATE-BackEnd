package com.otclub.humate.domain.chat.dto;

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
public class ChatRoomDetailDTO {
    private int chatRoomId;
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
    private String targetParticipateId;
    private String targetProfileImgUrl; // 상대방 프로필
    private int targetIsClicked; // 상대방 메이트 맺기 클릭 여부
}

