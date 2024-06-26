package org.service.urlshortener.health.controller;

import org.service.urlshortener.common.response.ResponseDto;
import org.service.urlshortener.common.response.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {
    @GetMapping("/api/v1/health")
    public ResponseEntity<?> getHealth() {
        return ResponseDto.toResponseEntity(ResponseMessage.SUCCESS, "health good~~!");
    }
}