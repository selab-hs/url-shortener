package com.urlshortener.auth.controller;

import com.urlshortener.auth.annotation.AuthMember;
import com.urlshortener.auth.domain.MemberDetail;
import com.urlshortener.auth.dto.JoinRequest;
import com.urlshortener.auth.service.AuthService;
import com.urlshortener.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getInformation(@AuthMember MemberDetail detail) {
        authService.parsingObjectNullCheck(detail);

        return ResponseDto.ok(detail);
    }
}