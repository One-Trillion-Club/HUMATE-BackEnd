package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.Message;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅방 내역 조회를 위한 DTO
 * @author 최유경
 * @since 2024.08.08
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.08  	최유경        최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomHistoryMessageResponseDTO {
    // 채팅방 상세 DTO
    RoomDetailDTO roomDetailDTO;
    // 채팅 메세지 리스
    List<Message> messageHistoryList;


    public static RoomHistoryMessageResponseDTO of(RoomDetailDTO roomDetailDTO, List<Message> messageHistoryList){
        return RoomHistoryMessageResponseDTO.builder()
                .roomDetailDTO(roomDetailDTO)
                .messageHistoryList(messageHistoryList)
                .build();
    }
}
