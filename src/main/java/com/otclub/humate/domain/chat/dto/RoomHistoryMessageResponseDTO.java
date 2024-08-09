package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.Message;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomHistoryMessageResponseDTO {
    RoomDetailDTO roomDetailDTO;
    List<Message> messageHistoryList;


    public static RoomHistoryMessageResponseDTO of(RoomDetailDTO roomDetailDTO, List<Message> messageHistoryList){
        return RoomHistoryMessageResponseDTO.builder()
                .roomDetailDTO(roomDetailDTO)
                .messageHistoryList(messageHistoryList)
                .build();
    }
}
