package com.otclub.humate.domain.chat.dto;

import lombok.*;

/**
 * 동행 생성을 위한 DTO
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanionCreateRequestDTO {
    // 채팅방 ID
    private String chatRoomId;
}
