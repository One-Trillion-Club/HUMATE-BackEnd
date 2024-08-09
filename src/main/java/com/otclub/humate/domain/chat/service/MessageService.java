package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.MessageRedisDTO;
import com.otclub.humate.domain.chat.dto.MessageRequestDTO;
import com.otclub.humate.domain.chat.dto.RoomHistoryMessageResponseDTO;
import com.otclub.humate.domain.chat.vo.Message;
import java.util.List;

/**
 * 채팅 메세지 서비스
 * @author 최유경
 * @since 2024.07.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.31  	최유경        최초 생성
 * 2024.08.01   최유경        채팅메세지 내역 조회
 * </pre>
 */
public interface MessageService {
    /**
     * 메세지 생성
     *
     * @author 최유경
     * @param requestDTO 메세지 전송 요청 정보
     */
    void createMessage(MessageRequestDTO requestDTO);
    /**
     * 메세지 생성
     *
     * @author 최유경
     * @param redisDTO 메세지 전송 요청 정보
     */
    void createMessage(MessageRedisDTO redisDTO);

    /**
     * 과거 메세지 리스트 조회
     *
     * @param roomId 채팅방 ID
     * @return List 채팅 메세지 조회
     */
    List<Message> getListMessage(String roomId);
}
