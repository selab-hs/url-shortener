package com.urlshortener.batch.job;

import com.urlshortener.actionlog.domain.SystemActionLog;
import com.urlshortener.actionlog.repository.SystemActionLogRepository;
import com.urlshortener.shortener.domain.ShortUrl;
import com.urlshortener.shortener.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemActionLogJob {
    private final SystemActionLogRepository systemActionLogRepository;
    private final ShortUrlRepository shortUrlRepository;

    public void run() {
        // 로그 조회
        var systemActionLog = systemActionLogRepository.findNullMemberIdLogsLimitedTo1000();

        var salIds = systemActionLog
                .stream()
                .map(SystemActionLog::getUrlId)
                .toList();

        // List<ShortUrl>를 Map<Long, ShortUrl>로 변환
        Map<Long, ShortUrl> shortUrlMap = shortUrlRepository.findAllByIdIn(salIds).stream()
                .collect(Collectors.toMap(ShortUrl::getId, shortUrl -> shortUrl));

        // 싱크 맞춰주기
        var updatedSystemActionLogs = systemActionLog.stream().peek(sa -> {
                    var shortUrl = shortUrlMap.get(sa.getUrlId());

                    if (shortUrl == null) {
                        sa.updateMember(-1L);
                    } else {
                        if (shortUrl.getMemberId() != null && shortUrl.getMemberId() != -1L) {
                            sa.updateMember(shortUrl.getMemberId());
                        }
                    }
                }
        ).toList();

        systemActionLogRepository.saveAll(updatedSystemActionLogs);
    }
}
