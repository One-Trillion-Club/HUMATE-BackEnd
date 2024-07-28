package com.otclub.humate.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanionActivityHistory {
    private int companionActivityId;
    private int companionId;
    private int activityId;
    private int status;
    private Date createdAt;
}
