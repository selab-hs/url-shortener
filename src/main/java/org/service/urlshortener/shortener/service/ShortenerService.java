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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortenerService {

    private final ShortenerRepository shortenerRepository;
    private final EncryptionService encryptionService;

    public ShortUrlResponse createShortUrl(LongUrlRequest request) {

        if (shortenerRepository.findByLongUrl(request.getLongUrl()).isPresent()) {
            var shortUrl = shortenerRepository.findByLongUrl(request.getLongUrl()).get().getShortUrl();
            return new ShortUrlResponse(shortUrl);
        }

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

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeSixMonthsOldData() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<Url> oldDates = shortenerRepository.findByCreatedAtBefore(sixMonthsAgo);
        shortenerRepository.deleteAll(oldDates);
    }
}
