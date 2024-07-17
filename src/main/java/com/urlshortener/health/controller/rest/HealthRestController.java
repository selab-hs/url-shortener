package com.urlshortener.health.controller.rest;

import com.urlshortener.common.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRestController {
    @GetMapping("/api/v1/health")
    public ResponseEntity<?> getHealth() {
        return ResponseDto.ok("health good~~!");
    }
}