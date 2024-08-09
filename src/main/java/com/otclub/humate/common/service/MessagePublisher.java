package com.otclub.humate.common.service;

import com.otclub.humate.domain.chat.vo.Message;

/**
 * MessagePublisher
 *
 * @author 최유경
 * @since 2024.08.02
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.02  	최유경        최초 생성
 * </pre>
 */
public interface MessagePublisher {
    /**
     * Redis 메시지 발행
     *
     * @author 최유경
     * @param channel 구독한 채널
     * @param message 보낼 메세지
     */
    void publish(String channel, Message message);
}
