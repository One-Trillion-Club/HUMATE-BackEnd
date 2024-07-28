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
public class ChatParticipate {
    private int participantId;
    private String memberId;
    private int chatRoomId;
    private Date createdAt;
    private Date exitedAt;
    private int isClicked;
}
