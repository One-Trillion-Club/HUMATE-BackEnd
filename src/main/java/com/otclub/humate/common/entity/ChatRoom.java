package com.otclub.humate.common.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatRoom {
    private int chatRoomId;
    private int postId;
    private String postTitle;
    private String matchDate;
    private String matchBranch;
    private String chatStatus;
    private int isMatched;
    private Date createdAt;

    public static ChatRoom from(int postId){
        return ChatRoom.builder()
                .postId(postId)
                .build();
    }
}
