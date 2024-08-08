package com.otclub.humate.common.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * 활동 인증 내역 Entity
 * @author 손승완
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28   손승완        최초 생성
 *
 * </pre>
 */
@Getter
@Builder
public class CompanionActivityHistory {
    private int companionActivityId;
    private int companionId;
    private int activityId;
    private int status; // 0 : 대기, 1 : 완료
    private Date createdAt;
    private static final int PENDING_STATUS = 0;

    public static CompanionActivityHistory of(int companionId, int activityId) {
        return CompanionActivityHistory.builder()
                .companionId(companionId)
                .activityId(activityId)
                .status(PENDING_STATUS)
                .build();
    }

}
