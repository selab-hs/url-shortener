package org.service.urlshortener.shortener.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.common.response.ResponseDto;
import org.service.urlshortener.common.response.ResponseMessage;
import org.service.urlshortener.shortener.dto.request.LongUrlRequest;
import org.service.urlshortener.shortener.dto.request.ShortUrlRequest;
import org.service.urlshortener.shortener.service.ShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/short")
public class ShortenerController {

    private final ShortenerService shortenerService;

    @PostMapping
    public ResponseEntity<?> setUrlShort(@RequestBody LongUrlRequest longUrlRequest){
        var shortUrl = shortenerService.createShortUrl(longUrlRequest).getShortUrl();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, shortUrl);
    }

    @GetMapping
    public ResponseEntity<?> getOriginUrl(@RequestBody ShortUrlRequest shortUrlRequest){
        var originUrl = shortenerService.getOriginUrl(shortUrlRequest).getOriginUrl();

        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, originUrl);
    }
}
