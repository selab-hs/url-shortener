package com.urlshortener.shortener.controller.rest;

import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.ratelimit.annotation.RateLimit;
import com.urlshortener.shortener.dto.request.OriginUrlRequest;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import com.urlshortener.shortener.service.ShortenerService;
import jakarta.annotation.Nullable;
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

    /**
     * Origin Url 을 Short Url 로 변환하는 API
     *
     * @param originUrlRequest
     * @return shortUrl 를 반환합니다.
     */
    @RateLimit(value = 10, durationMinutes = 2)
    @PostMapping("/api/v1/short")
    public ResponseEntity<?> createShortUrl(
            /** 인증되지 않은 사용자도 사용 가능해야 한다. */
            @Nullable AuthUser member,
            @RequestBody OriginUrlRequest originUrlRequest
    ) {
        var shortUrl = shortenerService.createShortUrl(member, originUrlRequest).getShortCode();

        return ResponseDto.created(shortUrl);
    }

    /**
     * Short Url 요청을 Origin Url 로 리다이렉트하는 API
     *
     * @param shortCode
     * @param response
     * @throws IOException
     */
    @GetMapping("/{shortCode}")
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
