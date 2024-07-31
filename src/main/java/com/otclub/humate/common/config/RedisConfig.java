package com.otclub.humate.common.config;

import com.otclub.humate.common.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 연동 config
 * @author 최유경
 * @since 2024.07.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.31  	최유경        최초 생성
 * </pre>
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {
    private final RedisProperties redisProperties;

    /**
     * 단일 Topic 사용을 위한 Bean 설정
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
    }

    /**
     * RedisTemplate 구성하기
     * @apiNote Redis는 default로 key-value를 String(Hash)를 저장
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<?,?> redisTemplate(){ // redisTemplate default Value Type = JSON
        RedisTemplate<?,?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        //Connection
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));

        return redisTemplate;
    }


    /**
     * 발행된 메시지 처리를 위한 리스너 설정
     * @apiNote Redis Channel(Topic)로 부터 메시지를 받고, 메세지 리스너에 대한 비동기 동작을 제공하는 컨테이너
     * @param connectionFactory
     * @param listenerAdapter
     * @param channelTopic
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter,
            ChannelTopic channelTopic
    ){
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, channelTopic);

        return container;
    }

    /**
     * 메시지 처리하는 메서드
     * @apiNote RedisMessageListenerContainer 로부터 메시지를 dispatch 받고, Subscriber 에서 처리
     * @param subscriber 실제 메시지를 처리하는 비즈니스 로직
     * @return MessageListenerAdapter
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(RedisSubscriber subscriber){
        return new MessageListenerAdapter(subscriber, "sendMessage");
    }

    /**
     * Topic 공유를 위해 Channel Topic 을 빈으로 등록
     * @return
     */
    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("chat");
    }

}
