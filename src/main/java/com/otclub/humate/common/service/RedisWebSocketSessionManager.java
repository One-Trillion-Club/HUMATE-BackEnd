package com.otclub.humate.common.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisWebSocketSessionManager {
    private static final String USER_SESSION_PREFIX = "user:session:";
    private final ConcurrentMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final RedisTemplate<String, Object> redisTemplate;

    public void addSession(String memberId, WebSocketSession session){
        String key = USER_SESSION_PREFIX + memberId;
        log.info("[RedisWebSocketSessionManager] - addSession key {} ", key);
        log.info("[RedisWebSocketSessionManager] - session.toString() {} ", session.toString());

        sessions.put(memberId, session);
        redisTemplate.opsForValue().set(key, session.toString(), 1, TimeUnit.HOURS); // 세션 타임아웃 설정
    }

    public void removeSession(String userId) {
        String key = USER_SESSION_PREFIX + userId;
        sessions.remove(key);
        redisTemplate.delete(key);
    }

    public WebSocketSession getSession(String userId) {
        String key = USER_SESSION_PREFIX + userId;
        //return (WebSocketSession) redisTemplate.opsForValue().get(key);
        return sessions.get(userId);
    }

    public boolean isUserConnected(String userId) {
        String key = USER_SESSION_PREFIX + userId;

        log.info("[SessionManager] isUserConnected key : {}, {} ", key, redisTemplate.hasKey(key));
        return redisTemplate.hasKey(key);
    }
}
