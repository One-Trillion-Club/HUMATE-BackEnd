package com.otclub.humate.common.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 채팅방 정보 Entity
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
public class ChatRoom {
    // 채팅방 ID
    private int chatRoomId;
    // 매칭글 ID
    private int postId;
    // 매칭글 제목
    private String postTitle;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 채팅 상태
    private String chatStatus;
    // 매칭 여부
    private int isMatched;
    // 생성 날짜
    private Date createdAt;

    public static ChatRoom from(Post post){
        return ChatRoom.builder()
                .postId(post.getPostId())
                .postTitle(post.getTitle() == null ? "-" : post.getTitle())
                .matchDate(post.getMatchDate() == null ? "-" : post.getMatchDate())
                .matchBranch(post.getMatchBranch() == null ? "-" : post.getMatchBranch())
                .build();
    }
}
