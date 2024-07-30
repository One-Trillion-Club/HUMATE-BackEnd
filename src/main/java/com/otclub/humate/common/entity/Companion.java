package com.otclub.humate.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Companion {
    private int companionId;
    private int chatRoomId;
    private String firstMemberId;
    private String secondMemberId;
    private Date createdAt;
    private Date finishedAt;
}
