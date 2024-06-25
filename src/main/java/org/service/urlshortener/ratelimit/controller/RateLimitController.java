package org.service.urlshortener.ratelimit.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.service.urlshortener.common.response.ResponseDto;
import org.service.urlshortener.common.response.ResponseMessage;
import org.service.urlshortener.ratelimit.service.RateLimitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/limit")
public class RateLimitController {
    // 테스트용 컨트롤러 이후 URL Shorter 에서 Service만 의존성 추가하여 사용 예정
    private final RateLimitService rateLimitService;

    @GetMapping
    public ResponseEntity<?> getData(@CookieValue(value = "client_id", required = false) String userId, HttpServletResponse response) {
        if (userId == null) {
            userId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("client_id", userId);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(cookie);
        }

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, rateLimitService.tryConsume());
    }
}
