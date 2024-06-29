package org.service.urlshortener.batch.job;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.service.urlshortener.shortener.domain.OriginUrl;
import org.service.urlshortener.shortener.repository.OriginUrlRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UrlDeleteJob {
    private final OriginUrlRepository OriginUrlRepository;

    @Transactional
    public void removeSixMonthsOldData() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<OriginUrl> oldDates = OriginUrlRepository.findByCreatedAtBefore(sixMonthsAgo);
        OriginUrlRepository.deleteAll(oldDates);
    }
}
