package com.urlshortener.batch.job;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.urlshortener.shortener.domain.ShortUrl;
import com.urlshortener.shortener.repository.ShortUrlRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UrlDeleteJob {
    private final ShortUrlRepository OriginUrlRepository;

    @Transactional
    public void removeSixMonthsOldData() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<ShortUrl> oldDates = OriginUrlRepository.findByCreatedAtBefore(sixMonthsAgo);
        OriginUrlRepository.deleteAll(oldDates);
    }
}
