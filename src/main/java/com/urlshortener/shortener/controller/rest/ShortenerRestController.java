package com.urlshortener.shortener.controller.rest;

import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.ratelimit.annotation.RateLimit;
import com.urlshortener.shortener.dto.request.OriginUrlRequest;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import com.urlshortener.shortener.dto.response.ShortCodeAndSystemActionLogResponse;
import com.urlshortener.shortener.dto.response.ShortCodeResponse;
import com.urlshortener.shortener.service.ShortenerService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.urlshortener.ratelimit.annotation.RateLimitType.CREATE_SHORT_URL;

@Tag(name = "url 단축기")
@Slf4j
@RestController
@RequiredArgsConstructor
public class ShortenerRestController {
    private final ShortenerService shortenerService;

    /**
     * Origin Url 을 Short Url 로 변환하는 API
     * 인증되지 않은 사용자도 사용 가능
     *
     * @param originUrlRequest 요청 받은 원본 URI
     * @param request          요청 액션 로그 저장을 위한 Servlet Request
     * @return shortUrl 를 반환합니다.
     */
    @RateLimit(type = CREATE_SHORT_URL, value = 10, durationMills = 60000)
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Origin Url 을 Short Url 로 변환"
            , content = @Content(schema = @Schema(implementation = ShortCodeResponse.class)))
    })
    @PostMapping("/api/v1/shorts")
    public ResponseEntity<?> createShortUrl(
            /** 인증되지 않은 사용자도 사용 가능해야 한다. */
            @Nullable AuthUser user,
            @RequestBody OriginUrlRequest originUrlRequest,
            HttpServletRequest request
    ) {
        var shortUrl = shortenerService.createShortUrl(
                user,
                originUrlRequest,
                request
        ).getShortCode();

        return ResponseDto.created(shortUrl);
    }

    /**
     * Short Url 요청을 Origin Url 로 리다이렉트하는 API
     *
     * @param shortCode 요청 받은 최적화 code
     * @param response  헤더에 값을 넣기 위한 HttpResponse
     * @throws IOException
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "302", description = "성공적으로 기존 Origin URL 리다이렉션 성공")})
    @GetMapping("/{shortCode}")
    public void getOriginUrl(
            @PathVariable("shortCode") ShortCodeRequest shortCode,
            HttpServletResponse response
    ) throws IOException {
        log.debug("shortCode = {}", shortCode);
        var originUrl = shortenerService.getOriginUrl(shortCode).getOriginUrl();
        log.debug("originUrl = {}", originUrl);

        response.sendRedirect(originUrl);
    }

    /**
     * 사용자가 생성했던 shortCode 리스트 반환 API
     *
     * @param user 로그인 유저 정보
     * @return List 유저가 생성한 url List
     */
    @GetMapping("/api/v1/shorts")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "로그인 회원의 생성 URL 모두 조회",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ShortCodeAndSystemActionLogResponse.class))))
    })
    public ResponseEntity<?> findByMemberId(AuthUser user) {
        var result = shortenerService.getFindByMemberId(user);

        return ResponseDto.ok(result);
    }
}
