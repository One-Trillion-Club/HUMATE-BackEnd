package com.otclub.humate.common.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * 활동 인증 내역 Entity
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30   손승완        최초 생성
 *
 * </pre>
 */
@Getter
@Builder
public class CompanionActivityHistory {
    // 활동 인증 내역 ID
    private int companionActivityId;

    // 동행 ID
    private int companionId;

    // 활동 ID
    private int activityId;

    // 인증 상태 (0 : 대기, 1 : 완료)
    private int status;

    // 생성 날짜
    private Date createdAt;

    // 대기 상태를 나타내는 상수
    private static final int PENDING_STATUS = 0;

    public static CompanionActivityHistory of(int companionId, int activityId) {
        return CompanionActivityHistory.builder()
                .companionId(companionId)
                .activityId(activityId)
                .status(PENDING_STATUS)
                .build();
    }

}
