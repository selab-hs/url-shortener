package org.service.urlshortener.batch.scheudler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.batch.job.UrlDeleteJob;
import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.url.NotFinishRemoveSixMonthsOldDataException;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class UrlDeleteScheduler {
    private final UrlDeleteJob job;

    @Scheduled(cron = "0 0 0 * * ?")
    public void runRemoveSixMonthsOldData() {
        try {
            job.removeSixMonthsOldData();
        } catch (Exception e) {
            throw new NotFinishRemoveSixMonthsOldDataException(ErrorMessage.NOT_FINISH_DELETE_SIX_MONTHS_OLD_DATA);
        }
    }
}