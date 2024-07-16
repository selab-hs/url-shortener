package com.urlshortener.auth.controller.rest;

import com.urlshortener.auth.dto.JoinRequest;
import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.auth.service.AuthService;
import com.urlshortener.common.response.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 및 인가 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JoinRequest request) {
        var userDetail = authService.userLogin(request);

        return ResponseDto.created(userDetail);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInformation(AuthUser user) {
        var response = authService.loadUserById(user.getId());

        return ResponseDto.ok(response);
    }
}