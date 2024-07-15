package com.urlshortener.actionlog.controller.rest;

import com.urlshortener.actionlog.application.SystemActionLogService;
import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/system-action-logs")
@RequiredArgsConstructor
public class SystemActionLogRestController {
    private final SystemActionLogService systemActionLogService;

    /**
     * 해당 shortCode 의 접근 정보를 전체 조회하는 API
     *
     * @param shortCode
     * @return List
     */
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> getShortUrl(@PathVariable("shortCode") ShortCodeRequest shortCode) {
        var shortcodeLogs = systemActionLogService.getAllShortcodeViews(shortCode);

        return ResponseDto.ok(shortcodeLogs);
    }

    /**
     * 해당 shortCode 의 조회수를 조회 하는 API
     *
     * @param shortCode
     * @return
     */
    @GetMapping("/{shortCode}/views")
    public ResponseEntity<?> getShortUrlViewCount(@PathVariable("shortCode") ShortCodeRequest shortCode) {
        var shortcodeViewCount = systemActionLogService.getShortcodeViewCount(shortCode);

        return ResponseDto.ok(shortcodeViewCount);
    }
}
