package com.urlshortener.actionlog.application;

import com.urlshortener.actionlog.dto.ShortcodeViewCountResponse;
import com.urlshortener.actionlog.dto.SystemActionLogResponse;
import com.urlshortener.actionlog.repository.SystemActionLogRepository;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import com.urlshortener.shortener.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SystemActionLogService {
    private final SystemActionLogRepository systemActionLogRepository;
    private final EncryptionService encryptionService;

    /**
     * 하루동안 발생한 로그의 수 를 제공하는 메서드
     *
     * @return
     */
    public Long countApiCallCount() {
        var endAt = LocalDateTime.now();
        var startAt = endAt.minusDays(1);

        return systemActionLogRepository.countByCreatedAtBetween(startAt, endAt);
    }

    /**
     * 요청 받은 Shortcode 를 요청한 상세 내역을 제공하는 메서드
     *
     * @param request
     * @return List<SystemActionLogResponse>
     */
    public List<SystemActionLogResponse> getAllShortcodeViews(ShortCodeRequest request) {
        var logs = systemActionLogRepository.findByUrlId(encryptionService.decode(request.getShortCode()));
        return logs.stream()
                .map(
                        (log) -> SystemActionLogResponse.from(
                                log.getIpAddress(),
                                log.getHttpMethod(),
                                log.getPath(),
                                log.getUserAgent(),
                                log.getHost(),
                                log.getReferer()
                        ))
                .collect(Collectors.toList());
    }

    /**
     * 요창 받은 Shortcode 의 접속 회수를 제공하는 메서드
     *
     * @param request
     * @return Long
     */
    public ShortcodeViewCountResponse getShortcodeViewCount(ShortCodeRequest request) {

        return ShortcodeViewCountResponse.from(
                systemActionLogRepository.countByUrlId(
                        encryptionService.decode(
                                request.getShortCode())));
    }
}