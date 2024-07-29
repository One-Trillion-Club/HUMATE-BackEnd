package com.otclub.humate.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
public class CompanionActivityHistory {
    private int companionActivityId;
    private int companionId;
    private int activityId;
    private int status; // 0 : 대기, 1 : 완료
    private Date createdAt;
    private String activityTitle;

    public static CompanionActivityHistory of(int companionId, int activityId) {
        return CompanionActivityHistory.builder()
                .companionId(companionId)
                .activityId(activityId)
                .status(0)
                .build();
    }

}
