package com.otclub.humate.domain.companion.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 동행 상태 enum 클래스
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 *
 * </pre>
 */
@Getter
@RequiredArgsConstructor
public enum CompanionStatus {
    COMPLETED("완료"),
    IN_PROGRESS("진행중");

    private final String status;
}
