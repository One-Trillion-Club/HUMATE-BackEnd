package com.otclub.humate.common.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * 세션 관련 처리 manager
 *
 * @author 최유경
 * @since 2024.08.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  	최유경        최초 생성
 * 2024.08.05   최유경        세션 생성, 세션 삭제 추가 처리
 * </pre>
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RedisWebSocketSessionManager {
    @Value("${session.prefix.user}")
    private String USER_SESSION_PREFIX;
    private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 세션 추가
     *
     * @author 최유경
     * @param participateId 참여자 ID
     * @param session 세션
     */
    public void addSession(String participateId, WebSocketSession session){
        String key = getKey(participateId);
        log.info("[RedisWebSocketSessionManager] - addSession key {} ", key);
        log.info("[RedisWebSocketSessionManager] - session.toString() {} ", session.toString());

        sessions.put(session.getId(), session);
        redisTemplate.opsForValue().set(key, session.getId(), 1, TimeUnit.HOURS); // 세션 타임아웃 설정
    }

    /**
     * 세션 삭제
     *
     * @author 최유경
     * @param participateId 참여자 ID
     */
    public void removeSession(String participateId) {
        String key = getKey(participateId);
        if(redisTemplate.hasKey(key)){
            String sessionId = (String) redisTemplate.opsForValue().get(key);
            if(sessions.containsKey(sessionId))
                sessions.remove(sessionId);
            redisTemplate.delete(key);
        }
    }

    /**
     * 세션 조회
     *
     * @author 최유경
     * @param participateId 참여자 ID
     * @return 웹소켓
     */
    public WebSocketSession getSession(String participateId) {
        String key = getKey(participateId);
        String sessionId = (String) redisTemplate.opsForValue().get(key);
        log.info("[SessionManager] getSession key sessionId : {}, {} ", key, sessionId);
        return sessions.get(sessionId);
    }

    /**
     * 사용자 연결 여부 확인
     *
     * @author 최유경
     * @param participateId 참여자 ID
     * @return boolean
     */
    public boolean isUserConnected(String participateId) {
        String key = getKey(participateId);

        log.info("[SessionManager] isUserConnected key : {}, {} ", key, redisTemplate.hasKey(key));
        return redisTemplate.hasKey(key);
    }

    /**
     * prefix 붙여서 key 생성
     *
     * @author 최유경
     * @param participateId 참여자 ID
     * @return String key
     */
    private String getKey(String participateId){
        return USER_SESSION_PREFIX + participateId;
    }
}
