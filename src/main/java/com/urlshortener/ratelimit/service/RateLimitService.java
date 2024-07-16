package com.urlshortener.ratelimit.service;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.BusinessException;
import com.urlshortener.ratelimit.annotation.RateLimit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimitService {
    private final StringRedisTemplate redisTemplate;

    /**
     * Client UUID 조회를 통한 요청 횟수 유효성 검사
     *
     * @param clientId  -> Client UUID : 일정 기간 내의 요청 횟수를 가져오기 위한 UUID key
     * @param rateLimit -> 요청 제한 횟수, 제한 시간 (Minutes) 설정
     * @return boolean -> 요청 횟수 성공 여부
     */
    public boolean tryConsume(String clientId, RateLimit rateLimit) {
        var key = "rate-limit:" + clientId;
        var currentCount = redisTemplate.opsForValue().increment(key, 1);

        if (currentCount == null) {
            throw new BusinessException(ErrorMessage.INTERNAL_SERVER_ERROR);
        }

        if (currentCount == 1) {
            var durationMills = rateLimit.durationMills();
            redisTemplate.expire(key, Duration.ofMillis(durationMills));
        }

        return currentCount <= rateLimit.value();
    }
}