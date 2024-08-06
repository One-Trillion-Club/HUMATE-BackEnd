package com.otclub.humate.domain.companion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CompanionDTO {
    private int companionId;
    private int chatRoomId;
    private String firstMemberId;
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
