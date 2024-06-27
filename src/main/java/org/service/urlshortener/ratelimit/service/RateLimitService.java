package org.service.urlshortener.ratelimit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimitService {
    private final RedisTemplate<String, String> redisTemplate;

    public boolean tryConsume(String clientId) {
        String key = "rate_limit:" + clientId;
        log.info("key : {}", key);
        log.info("redis 시작");
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Long currentCount = null;

        try {
            currentCount = ops.increment(key, 1);
            log.info("currentCount : {}", currentCount);

            if (currentCount == 1) {
                redisTemplate.expire(key, Duration.ofMinutes(2));
                log.info("expire 설정");
            }
        } catch (Exception e) {
            log.error("Redis 작업 중 오류 발생: {}", e.getMessage(), e);
        }

        log.info("redis 종료");

        return currentCount <= 10;
    }
}
