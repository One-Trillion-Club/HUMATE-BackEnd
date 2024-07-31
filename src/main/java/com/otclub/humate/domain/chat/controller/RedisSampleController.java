package com.otclub.humate.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisSampleController {
    private final StringRedisTemplate redisTemplate;

    @GetMapping("/{key}/{value}")
    public String stringsSet(@PathVariable("key") String key, @PathVariable("value") String value){
        log.info("Redis 테스트 ========= ");
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        operations.set(key, value);
        log.info("operations : {} ", operations.get(key));
        return "ok";
    }
}
