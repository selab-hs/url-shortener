package com.urlshortener.actionlog.event.listener;

import com.urlshortener.actionlog.domain.SystemActionLog;
import com.urlshortener.actionlog.event.model.SystemActionLogEvent;
import com.urlshortener.actionlog.repository.SystemActionLogRepository;
import com.urlshortener.auth.token.TokenProvider;
import com.urlshortener.shortener.service.EncryptionService;
import com.urlshortener.shortener.service.ShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemActionLogEventListener {
    private final SystemActionLogRepository systemActionLogRepository;
    private final TokenProvider tokenProvider;
    private final EncryptionService encryptionService;
    private final ShortenerService shortenerService;

    @Async(value = "systemActionLogExecutor")
    @EventListener(SystemActionLogEvent.class)
    public void subscribe(SystemActionLogEvent event) {
        var shortUrlId = encryptionService.decode(event.getPath().replace("/", ""));

        var systemActionLog = new SystemActionLog(
                event.getIpAddress(),

                /** memberId는 후처리 Batch에서 데이터 정합 진행 */
                -1L,
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
