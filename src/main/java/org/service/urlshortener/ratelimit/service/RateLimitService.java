package org.service.urlshortener.ratelimit.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimitService {
    private final RedisTemplate<String, String> redisTemplate;
    private final HttpServletRequest request;

    public boolean tryConsume() {
        String clientIdentifier = getClientCookie().orElse("no-cookie");
        String key = "rate_limit:" + clientIdentifier;
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Long currentCount = ops.increment(key, 1);

        if (currentCount == 1) {
            redisTemplate.expire(key, Duration.ofMinutes(5));
        }

        return currentCount <= 10;
    }

    private Optional<String> getClientCookie() {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        for (Cookie cookie : request.getCookies()) {
            if ("client_id".equals(cookie.getName())) {
                return Optional.of(cookie.getValue());
            }
        }

        return Optional.empty();
    }
}
