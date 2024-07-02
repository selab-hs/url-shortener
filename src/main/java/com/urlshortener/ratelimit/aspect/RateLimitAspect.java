package com.urlshortener.ratelimit.aspect;

import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.url.RateLimitExceededException;
import com.urlshortener.ratelimit.annotation.RateLimit;
import com.urlshortener.ratelimit.service.RateLimitService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitAspect {
    private final RateLimitService rateLimitService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    /**
     * UUID 사용한 요청 횟수 제한
     *
     * @param rateLimit -> 일정 시간 동안 (rateLimit.value()) 특정 횟수까지 요청 제한 (rateLimit.durationMinutes())
     * @throws RateLimitExceededException 일정 시간 요청 횟수 초과시 예외 발생
     */
    @Before("@annotation(rateLimit)")
    public void checkRateLimit(RateLimit rateLimit) {
        String clientId = getClientIdFromCookie(request, response);

        if (!rateLimitService.tryConsume(clientId, rateLimit)) {
            throw new RateLimitExceededException(ErrorMessage.RATE_LIMIT_EXCEEDED);
        }
    }

    /**
     * 쿠키 조회 및 쿠키가 없을 시 생성
     *
     * @param request 쿠키 조회, response 쿠키 등록
     * @return clientCookie UUID
     */
    private String getClientIdFromCookie(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            return createClientIdFromCookie(response);
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> "client-id".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseGet(() -> createClientIdFromCookie(response));
    }

    /**
     * 쿠키 생성 및 쿠키 저장
     *
     * @param response 쿠키 등록
     * @return clientCookie UUID
     */
    private String createClientIdFromCookie(HttpServletResponse response) {
        String clientId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("client-id", clientId);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 183);
        response.addCookie(cookie);

        return clientId;
    }
}