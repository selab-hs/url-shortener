package com.urlshortener.actionlog.event.listener;

import com.urlshortener.actionlog.domain.SystemActionLog;
import com.urlshortener.actionlog.event.model.SystemActionLogEvent;
import com.urlshortener.actionlog.repository.SystemActionLogRepository;
import com.urlshortener.shortener.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemActionLogEventListener {
    private final SystemActionLogRepository systemActionLogRepository;
    private final EncryptionService encryptionService;

    @Async(value = "systemActionLogExecutor")
    @EventListener(SystemActionLogEvent.class)
    public void subscribe(SystemActionLogEvent event) {
        var shortUrlId = encryptionService.decode(event.getPath().replace("/", ""));

        var systemActionLog = new SystemActionLog(
                event.getIpAddress(),
                event.getPath(),
                shortUrlId,
                event.getMethod(),
                event.getUserAgent(),
                event.getHost(),
                event.getReferer()
        );
        
        systemActionLogRepository.save(systemActionLog);
    }
}
