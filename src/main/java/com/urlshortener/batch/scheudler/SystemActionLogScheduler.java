package com.urlshortener.batch.scheudler;

import com.urlshortener.batch.job.SystemActionLogJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemActionLogScheduler {
    private final SystemActionLogJob systemActionLogJob;

    @Scheduled(fixedRate = 5000)
    public void run() {
        systemActionLogJob.run();
    }
}
