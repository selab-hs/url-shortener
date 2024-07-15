package com.urlshortener.batch.job;

import com.urlshortener.actionlog.domain.SystemActionLog;
import com.urlshortener.actionlog.repository.SystemActionLogRepository;
import com.urlshortener.shortener.repository.ShortUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemActionLogJob {
    private final SystemActionLogRepository systemActionLogRepository;
    private final ShortUrlRepository shortUrlRepository;

    public void run() {
        // 로그 조회
        var systemActionLog = systemActionLogRepository.findNullMemberIdLogsLimitedTo1000();

        var salIds = systemActionLog.stream().map(SystemActionLog::getUrlId).toList();

        // shortUrl 정보 조회
        var shortUrls = shortUrlRepository.findAllByIdIn(salIds);

        // shortUrl이 -1이거나 null이 아닌 진짜 memberId를 가지고 있다면,
        // 그정보를 systemActionLog에 맞추어 저장한다.

        // 요거 구성해주세요~
    }
}
