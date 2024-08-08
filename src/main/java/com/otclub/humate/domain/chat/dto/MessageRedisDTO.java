package com.otclub.humate.domain.chat.dto;

import com.otclub.humate.domain.chat.vo.MessageType;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * redis common DTO
 * @author 최유경
 * @since 2024.08.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  	최유경        최초 생성
 * </pre>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageRedisDTO {
    private String chatRoomId;
    private String participateId;
    private String content;
    private MessageType messageType;
    private Date createdAt;

    public static MessageRedisDTO from(MessageRequestDTO requestDTO){
        return MessageRedisDTO.builder()
                .chatRoomId(requestDTO.getChatRoomId())
                .participateId(requestDTO.getParticipateId())
                .content(requestDTO.getContent())
                .messageType(requestDTO.getMessageType())
                .build();
    }

    public static MessageRedisDTO ofMate(MateUpdateRequestDTO requestDTO, String nickname){
        MessageType messageType = (requestDTO.getIsClicked()==1 ? MessageType.MATE_ACTIVE : MessageType.MATE_INACTIVE);

        return MessageRedisDTO.builder()
                .chatRoomId(requestDTO.getChatRoomId())
                .participateId(requestDTO.getParticipateId())
                .content(nickname)
                .messageType(messageType.MATE_ACTIVE)
                .build();
    }
}
