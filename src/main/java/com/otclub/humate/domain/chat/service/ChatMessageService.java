package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import java.util.List;

/**
 * 채팅 메세지 서비스
 * @author 최유경
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30   최유경        채팅메세지 내역 조회
 * 2024.07.29  	최유경        최초 생성
 * </pre>
 */
public interface ChatMessageService {
    ChatMessage createMessage(String roomId, ChatMessageRedisDTO requestDTO);

    List<ChatMessage> getListMessage(String roomId);
}
