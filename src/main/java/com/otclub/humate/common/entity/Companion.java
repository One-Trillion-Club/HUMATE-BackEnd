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
    private int companionId;
    private int chatRoomId;
    private String firstMemberId;
    private String secondMemberId;
    private Date createdAt;
    private Date finishedAt;
}
