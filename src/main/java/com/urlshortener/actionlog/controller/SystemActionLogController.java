package com.urlshortener.actionlog.controller;

import com.urlshortener.actionlog.application.SystemActionLogService;
import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/action_logs")
@RequiredArgsConstructor
public class SystemActionLogController {

    private final SystemActionLogService systemActionLogService;

    @GetMapping
    public ResponseEntity<?> getShortUrl(@RequestBody ShortCodeRequest request) {
        var logs = systemActionLogService.getAllShortcodeViews(request);

        return ResponseDto.ok(logs);
    }

    @GetMapping("/views")
    public ResponseEntity<?> getShortUrlViewCount(@RequestBody ShortCodeRequest request) {
        var logs = systemActionLogService.getShortcodeViewCount(request);

        return ResponseDto.ok(logs);
    }
}
