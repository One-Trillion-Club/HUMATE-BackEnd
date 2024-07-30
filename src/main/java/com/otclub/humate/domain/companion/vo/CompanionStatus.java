package com.otclub.humate.domain.companion.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanionStatus {
    COMPLETED("완료"),
    IN_PROGRESS("진행중");

    private final String status;
}
