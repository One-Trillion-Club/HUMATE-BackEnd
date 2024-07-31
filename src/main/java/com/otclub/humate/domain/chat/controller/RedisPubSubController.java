package com.otclub.humate.domain.chat.controller;

import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.chat.dto.ChatMessageRequestDTO;
import com.otclub.humate.domain.chat.service.RedisPubSubService;
import com.otclub.humate.domain.chat.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/redis/pubsub")
public class RedisPubSubController {
    private final RedisPubSubService redisPubSubService;

    @PostMapping("/send")
    public ResponseEntity<CommonResponseDTO> sendMessage(@RequestBody ChatMessageRequestDTO requestDTO){
        log.info("[Redis 테스트] ========= ");
        log.info("[Redis] 메시지 : {}", requestDTO.toString());

        redisPubSubService.pubSendMessageChannel(requestDTO);
        return ResponseEntity.ok(new CommonResponseDTO(true, "문자 전송에 성공했습니다."));
    }

//    @GetMapping("/{key}/{value}")
//    public String stringsSet(@PathVariable("key") String key, @PathVariable("value") String value){
//        log.info("Redis 테스트 ========= ");
//        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//
//        operations.set(key, value);
//        log.info("operations : {} ", operations.get(key));
//        return "ok";
//    }
}
