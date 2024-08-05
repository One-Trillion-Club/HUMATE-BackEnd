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

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisWebSocketSessionManager {
    @Value("${session.prefix.user}")
    private String USER_SESSION_PREFIX;
    private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final RedisTemplate<String, Object> redisTemplate;

    public void addSession(String participateId, WebSocketSession session){
        String key = getKey(participateId);
        log.info("[RedisWebSocketSessionManager] - addSession key {} ", key);
        log.info("[RedisWebSocketSessionManager] - session.toString() {} ", session.toString());

        sessions.put(session.getId(), session);
        redisTemplate.opsForValue().set(key, session.getId(), 1, TimeUnit.HOURS); // 세션 타임아웃 설정
    }

    public void removeSession(String participateId) {
        String key = getKey(participateId);
        if(redisTemplate.hasKey(key)){
            String sessionId = (String) redisTemplate.opsForValue().get(key);
            if(sessions.containsKey(sessionId))
                sessions.remove(sessionId);
            redisTemplate.delete(key);
        }
    }

    public WebSocketSession getSession(String participateId) {
        String key = getKey(participateId);
        String sessionId = (String) redisTemplate.opsForValue().get(key);
        log.info("[SessionManager] getSession key sessionId : {}, {} ", key, sessionId);
        return sessions.get(sessionId);
    }

    public boolean isUserConnected(String participateId) {
        String key = getKey(participateId);

        log.info("[SessionManager] isUserConnected key : {}, {} ", key, redisTemplate.hasKey(key));
        return redisTemplate.hasKey(key);
    }

    private String getKey(String participateId){
        return USER_SESSION_PREFIX + participateId;
    }
}
