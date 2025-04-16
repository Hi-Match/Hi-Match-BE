package kr.co.himatch.thanksyouplz.config;

import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.retry.RetryConfig;

import java.time.Duration;

@Configuration
public class Resilience4jRetryConfig {

    @Bean
    public RetryConfig retryConfig() {
        return RetryConfig.custom()
                .maxAttempts(10) // 최대 재시도 횟수 (처음 시도 포함)
                .waitDuration(Duration.ofMillis(500)) // 재시도 간 대기 시간
                .build();
    }

    @Bean
    public RetryRegistry retryRegistry(RetryConfig retryConfig) {
        return RetryRegistry.of(retryConfig);
    }
}