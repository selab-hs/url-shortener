package com.urlshortener.shortener.comtroller.rest;

import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.ratelimit.annotation.RateLimit;
import com.urlshortener.ratelimit.aspect.RateLimitAspect;
import com.urlshortener.shortener.dto.request.OriginUrlRequest;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import com.urlshortener.shortener.service.ShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShortenerRestController {
    private final ShortenerService shortenerService;
    private final RateLimitAspect rateLimitAspect;

    /**
     * Origin Url 을 Short Url 로 변환하는 API
     *
     * @param originUrlRequest
     * @return shortUrl 를 반환합니다.
     */
    @RateLimit(value = 10, durationMinutes = 2)
    @PostMapping("/api/v1/short")
    public ResponseEntity<?> createShortUrl(
            @RequestBody OriginUrlRequest originUrlRequest,
            @RequestHeader("client-id") String clientId
    ) {
        var shortUrl = shortenerService.createShortUrl(originUrlRequest, clientId).getShortCode();
        log.debug("short={}", shortUrl);

        return ResponseDto.created(shortUrl);
    }

    /**
     * Short Url 요청을 Origin Url 로 리다이렉트하는 API
     *
     * @param shortCode
     * @param response
     * @throws IOException
     */
    @GetMapping("{shortCode}")
    public void getOriginUrl(
            @PathVariable("shortCode") ShortCodeRequest shortCode,
            HttpServletResponse response
    ) throws IOException {
        log.debug("shortCode = {}", shortCode);
        var originUrl = shortenerService.getOriginUrl(shortCode).getOriginUrl();
        log.debug("originUrl = {}", originUrl);

        response.sendRedirect(originUrl);
    }
}