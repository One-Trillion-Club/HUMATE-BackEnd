package com.otclub.humate.domain.companion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 동행 회원 정보 dto
 * @author 손승완
 * @since 2024.08.06
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.06  	손승완        최초 생성
 * </pre>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CompanionDTO {
    // 동행 ID
    private int companionId;
    // 채팅방 ID
    private int chatRoomId;
    // 첫 번째 회원 ID
    private String firstMemberId;
    // 두 번째 회원 ID
    private String secondMemberId;


    public static CompanionDTO of(int chatRoomId,
                                  String firstMemberId,
                                  String secondMemberId) {

        return CompanionDTO.builder()
                .chatRoomId(chatRoomId)
                .firstMemberId(firstMemberId)
                .secondMemberId(secondMemberId)
                .build();
    }
}
