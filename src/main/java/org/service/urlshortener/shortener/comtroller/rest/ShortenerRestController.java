package org.service.urlshortener.shortener.comtroller.rest;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.common.response.ResponseDto;
import org.service.urlshortener.common.response.ResponseMessage;
import org.service.urlshortener.shortener.domain.vo.UrlDomain;
import org.service.urlshortener.shortener.dto.request.OriginUrlRequest;
import org.service.urlshortener.shortener.dto.request.ShortCodeRequest;
import org.service.urlshortener.shortener.service.ShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    @PostMapping("/api/v1/short")
    public ResponseEntity<?> createShortUrl(
            @RequestBody OriginUrlRequest originUrlRequest
    ) {
        var shortUrl = UrlDomain.URL + shortenerService.createShortUrl(originUrlRequest).getShortCode();
        log.debug("short={}", shortUrl);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, shortUrl);
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
            @PathVariable("shortCode") String shortCode,
            HttpServletResponse response
    ) throws IOException {
        log.debug("shortUrl = {}", shortCode);
        var originUrl = shortenerService.getOriginUrl(new ShortCodeRequest(shortCode)).getOriginUrl();
        log.debug("originUrl = {}", originUrl);

        response.sendRedirect(originUrl);
    }

    /**
     * 메인 페이지로 리다이렉드하는 API
     */
    @GetMapping()
    public RedirectView getPage() {
        return new RedirectView("/main");
    }
}