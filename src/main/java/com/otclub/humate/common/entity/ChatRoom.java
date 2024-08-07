package com.otclub.humate.common.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 채팅방 Entity
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
@ToString
public class ChatRoom {
    private int chatRoomId;
    private int postId;
    private String postTitle;
    private String matchDate;
    private String matchBranch;
    private String chatStatus;
    private int isMatched;
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
