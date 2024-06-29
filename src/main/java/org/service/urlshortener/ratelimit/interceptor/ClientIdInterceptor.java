package org.service.urlshortener.ratelimit.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.url.RateLimitExceededException;
import org.service.urlshortener.ratelimit.service.RateLimitService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ClientIdInterceptor implements HandlerInterceptor {
    private final RateLimitService rateLimitService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, Object handler) {
        log.info("인터셉트 시작");
        var clientId = getClientIdFromCookie(request, response);

        if (!rateLimitService.tryConsume(clientId)) {
            log.info("인터셉트 횟수 오류");
            throw new RateLimitExceededException(ErrorMessage.RATE_LIMIT_EXCEEDED);
        }

        log.info("인터셉트 종료");
        return true;
    }

    private String getClientIdFromCookie(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            return createClientIdFromCookie(response);
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> "client_id".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElseGet(() -> createClientIdFromCookie(response));
    }

    private String createClientIdFromCookie(HttpServletResponse response) {
        String clientId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("client_id", clientId);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 183);
        response.addCookie(cookie);

        return clientId;
    }
}
