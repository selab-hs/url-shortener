package org.service.urlshortener.shortener.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.url.NotFoundUrl;
import org.service.urlshortener.shortener.domain.Url;
import org.service.urlshortener.shortener.dto.request.LongUrlRequest;
import org.service.urlshortener.shortener.dto.request.ShortUrlRequest;
import org.service.urlshortener.shortener.dto.response.OriginUrlResponse;
import org.service.urlshortener.shortener.dto.response.ShortUrlResponse;
import org.service.urlshortener.shortener.repository.ShortenerRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortenerService {

    private final ShortenerRepository shortenerRepository;
    private final EncryptionService encryptionService;

    public ShortUrlResponse createShortUrl(LongUrlRequest request) {
        shortenerRepository.save(new Url(request.getLongUrl()));
        Url url = shortenerRepository.findByLongUrl(request.getLongUrl())
                .orElseThrow(() -> new NotFoundUrl(ErrorMessage.NOT_FOUND_URL));
        String shortUrl = encryptionService.encode(encryptionService.hashLong(url.getId()));
        url.setShortUrl(shortUrl);
        shortenerRepository.flush();

        return new ShortUrlResponse(shortUrl);
    }


    public OriginUrlResponse getOriginUrl(ShortUrlRequest request) {
        var origin = shortenerRepository.findByShortUrl(request.getShortUrl())
                        .orElseThrow(() -> new NotFoundUrl(ErrorMessage.NOT_FOUND_URL));

        return new OriginUrlResponse(origin.getLongUrl());
    }
}
