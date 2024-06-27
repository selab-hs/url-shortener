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
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Long currentCount = ops.increment(key, 1);

        if (currentCount == 1) {
            redisTemplate.expire(key, Duration.ofMinutes(2));
        }

        return currentCount <= 10;
    }
}
