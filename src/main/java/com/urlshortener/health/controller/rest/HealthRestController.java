package com.urlshortener.health.controller.rest;

import com.urlshortener.common.response.ResponseDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {

    /**
     * 서버 health 확인 요청
     */
    @GetMapping("/api/v1/health")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "서버 상태 양호 String 반환"),
            @ApiResponse(responseCode = "500", description = "웹 서버 내부 에러")
    })
    public ResponseEntity<?> getHealth() {
        return ResponseDto.ok("health good~~!");
    }
}