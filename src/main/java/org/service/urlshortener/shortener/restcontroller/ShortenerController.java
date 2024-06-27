package org.service.urlshortener.shortener.restcontroller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.common.response.ResponseDto;
import org.service.urlshortener.common.response.ResponseMessage;
import org.service.urlshortener.shortener.domain.vo.UrlDomain;
import org.service.urlshortener.shortener.dto.request.OriginUrlRequest;
import org.service.urlshortener.shortener.dto.request.ShortUrlRequest;
import org.service.urlshortener.shortener.service.ShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShortenerController {

    private final ShortenerService shortenerService;

    /**
     *
     * Origin Url 을 Short Url 로 변환하는 API
     *
     * @param originUrlRequest
     * @return shortUrl 를 반환합니다.
     */
    @PostMapping("/api/v1/short")
    public ResponseEntity<?> createShortUrl(
            @RequestBody OriginUrlRequest originUrlRequest
    ) {
        var shortUrl = UrlDomain.URL + shortenerService.createShortUrl(originUrlRequest).getShortUrl();
        log.debug("short={}", shortUrl);

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, shortUrl);
    }

    /**
     *
     *  Short Url 요청을 Origin Url 로 리다이렉트하는 API
     *
     * @param shortUrl
     * @param response
     * @throws IOException
     */
    @GetMapping("{shortUrl}")
    public void getOriginUrl(
            @PathVariable("shortUrl") String shortUrl,
            HttpServletResponse response
    ) throws IOException {
        var originUrl =
                shortenerService.getOriginUrl(
                        new ShortUrlRequest(shortUrl.replace(UrlDomain.URL, ""))).getOriginUrl();
        log.debug("originUrl = {}", originUrl);

        response.sendRedirect(originUrl);
    }
}