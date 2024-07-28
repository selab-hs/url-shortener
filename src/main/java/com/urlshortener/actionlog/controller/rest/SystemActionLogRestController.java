package com.urlshortener.actionlog.controller.rest;

import com.urlshortener.actionlog.application.SystemActionLogService;
import com.urlshortener.actionlog.dto.SystemActionLogResponse;
import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "시스템 액션 로그 관리")
@RestController
@RequestMapping("/api/v1/system-action-logs")
@RequiredArgsConstructor
public class SystemActionLogRestController {
    private final SystemActionLogService systemActionLogService;

    /**
     * 해당 shortCode 의 접근 정보를 전체 조회하는 API
     *
     * @param shortCode 생성헀던 shortCode
     * @return List<SystemActionLogResponse> 시스템 액션 로그 응답 DTO 반환
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Short Code 리스트 가져오기 성공",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = SystemActionLogResponse.class))))
    })
    @GetMapping("/{shortCode}")
    public ResponseEntity<?> getShortUrl(@PathVariable("shortCode") ShortCodeRequest shortCode) {
        var shortcodeLogs = systemActionLogService.getAllShortcodeViews(shortCode);

        return ResponseDto.ok(shortcodeLogs);
    }

    /**
     * 해당 shortCode 의 조회수를 조회 하는 API
     *
     * @param shortCode 생성헀던 shortCode
     * @return Long Short Code 리다이렉션 count
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Short Code 리다이렉션 Count 가져오기 성공",
            content = @Content(schema = @Schema(type = "Long")))
    })
    @GetMapping("/{shortCode}/views")
    public ResponseEntity<?> getShortUrlViewCount(@PathVariable("shortCode") ShortCodeRequest shortCode) {
        var shortcodeViewCount = systemActionLogService.getShortcodeViewCount(shortCode);

        return ResponseDto.ok(shortcodeViewCount);
    }
}