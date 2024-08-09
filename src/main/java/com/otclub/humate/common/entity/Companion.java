package com.otclub.humate.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 동행 Entity
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30   손승완        최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Companion {
    // 동행 ID
    private int companionId;

    // 채팅방 ID
    private int chatRoomId;

    // 첫 번째 회원 ID
    private String firstMemberId;

    // 두 번째 회원 ID
    private String secondMemberId;

    // 동행 생성 날짜
    private Date createdAt;

    // 동행 완료 날짜
    private Date finishedAt;
}
