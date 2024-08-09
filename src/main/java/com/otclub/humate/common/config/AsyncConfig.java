package com.otclub.humate.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * aws s3 연동 config
 * @author 손승완
 * @since 2024.08.05
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.05   손승완        최초 생성
 * </pre>
 */
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    /**
     * S3 이미지 업로드를 위한 스레드 풀 설정
     *
     * @return 설정된 스레드 풀 반환
     */
    @Bean("S3ImageUploadExecutor")
    public Executor imageUploadExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadGroupName("S3ImageUploadExecutor");
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() * 2);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        executor.initialize();

        return executor;
    }
}
