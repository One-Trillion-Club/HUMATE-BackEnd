package com.otclub.humate.domain.chat.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChatRoomVO {
    private int chatRoomId;
    private int postId;
    private String postTitle;
    private String matchDate;
    private String matchBranch;
    private String chatStatus;
    private int isMatched;
    private Date createdAt;
}
