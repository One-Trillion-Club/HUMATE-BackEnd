package com.otclub.humate.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket // 웹소켓 서버 사용
@EnableWebSocketMessageBroker // STOMP 사용
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 해당 파라미터의 접두사가 붙은 목적지(구독자)에 메시지를 보냄
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/send");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 엔드포인트 추가 등록
        registry.addEndpoint("/ws") // 엔드포인트
                .addInterceptors()
                .setAllowedOriginPatterns("*");
    }
}
