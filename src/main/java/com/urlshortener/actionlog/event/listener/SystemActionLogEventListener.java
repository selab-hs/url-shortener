package com.urlshortener.actionlog.event.listener;

import com.urlshortener.actionlog.domain.SystemActionLog;
import com.urlshortener.actionlog.event.model.SystemActionLogEvent;
import com.urlshortener.actionlog.repository.SystemActionLogRepository;
import com.urlshortener.auth.token.TokenProvider;
import com.urlshortener.shortener.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static com.urlshortener.util.HttpUtil.TOKEN_CODE;

@Component
@RequiredArgsConstructor
public class SystemActionLogEventListener {
    private final SystemActionLogRepository systemActionLogRepository;
    private final TokenProvider tokenProvider;
    private final EncryptionService encryptionService;

    @Async(value = "systemActionLogExecutor")
    @EventListener(SystemActionLogEvent.class)
    public void subscribe(SystemActionLogEvent event) {
        var systemActionLog = new SystemActionLog(
                event.getIpAddress(),
                event.getPath(),
                encryptionService.decode(event.getPath().replace("/", "")),
                event.getMethod(),
                event.getUserAgent(),
                event.getHost(),
                event.getReferer()
        );
        systemActionLogRepository.save(systemActionLog);
    }

    private String convertTokenOrIpAddress(String ipAddress) {
        if (ipAddress.contains(TOKEN_CODE)) {

            return String.valueOf(tokenProvider.getUserToken(ipAddress.replace(TOKEN_CODE, "")));
        }

        return ipAddress;
    }
}
