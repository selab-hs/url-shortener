package org.service.urlshortener.batch;

import lombok.RequiredArgsConstructor;
import org.service.urlshortener.shortener.domain.OriginUrl;
import org.service.urlshortener.shortener.repository.OriginUrlRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class  ScheduleService{
    private final OriginUrlRepository OriginUrlRepository;

    public void removeSixMonthsOldData() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<OriginUrl> oldDates = OriginUrlRepository.findByCreatedAtBefore(sixMonthsAgo);
        OriginUrlRepository.deleteAll(oldDates);
    }
}
