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
public class ChatParticipantVO {
    private int participantId;
    private int memberId;
    private int chatRoomId;
    private Date createdAt;
    private Date exitedAt;
    private int isClicked;
}
