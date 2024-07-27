package com.urlshortener.ratelimit.facade;

import com.urlshortener.ratelimit.annotation.RateLimit;
import com.urlshortener.ratelimit.repository.RedisLockRepository;
import com.urlshortener.ratelimit.service.RateLimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LettuceLockStockFacade {
    private final RateLimitService service;
    private final RedisLockRepository repository;

    public boolean limitIncrease(String clientId, RateLimit rateLimit) throws InterruptedException {
        while (!repository.lock(clientId)) {
            log.error("lock={}", clientId);
            Thread.sleep(100);
        }
        try {
            return service.tryConsume(clientId, rateLimit);
        } finally {
            repository.unlock(clientId);
        }
    }
}