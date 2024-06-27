package org.service.urlshortener.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.url.NotFinishRemoveSixMonthsOldData;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

    @Scheduled(cron = "0 0 0 * * ?")
    public void runRemoveSixMonthsOldData() {
        try {
            service.removeSixMonthsOldData();
        } catch (Exception e) {
            throw new NotFinishRemoveSixMonthsOldData(ErrorMessage.NOT_FINISH_DELETE_SIX_MONTHS_OLD_DATA);
        }
    }
}