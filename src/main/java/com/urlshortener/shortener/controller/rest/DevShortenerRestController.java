package com.urlshortener.shortener.controller.rest;

import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.shortener.dto.request.OriginUrlRequest;
import com.urlshortener.shortener.service.DevShortenerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[DEV] url 단축기")
@Slf4j
@RestController
@RequiredArgsConstructor
public class DevShortenerRestController {
    private final DevShortenerService devShortenerService;

    @PostMapping("/api/dev/v1/short")
    public ResponseEntity<?> createShortUrl(
            /** 인증되지 않은 사용자도 사용 가능해야 한다. */
            @Nullable AuthUser member,
            @RequestBody OriginUrlRequest originUrlRequest,
            HttpServletRequest request
    ) {
        var shortUrl = devShortenerService.createShortUrl(
                member,
                originUrlRequest,
                request
        ).getShortCode();

        return ResponseDto.created(shortUrl);
    }
}
