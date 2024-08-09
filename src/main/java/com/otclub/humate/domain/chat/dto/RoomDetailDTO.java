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
public class RoomDetailDTO {
    // 채팅방 ID
    private int chatRoomId;
    // 회원 ID
    private String memberId;
    // 참여자 ID
    private int participateId;
    // 매칭글 ID
    private int postId;
    // 매칭글 제목
    private String postTitle;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 상대방 메이트 맺기 클릭 여부
    private int isClicked;
    // 매칭 여부
    private int isMatched;
//    private String latestContent; // 마지막 메시지
//    private String latestContentTime; // 마지막 메시지 전송 시간

    // 상대방 닉네임
    private String targetNickname;
    // 상대방 회원 ID
    private String targetMemberId;
    // 상대방 참여 ID
    private String targetParticipateId;
    // 상대방 프로필 사진
    private String targetProfileImgUrl;
    // 상대방 메이트 맺기 클릭 여부
    private int targetIsClicked;
}

