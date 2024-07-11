package com.urlshortener.member.controller;

import com.urlshortener.member.service.RegisterMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
@Slf4j
public class RegisterMemberController {
    private final RegisterMemberService registerMemberService;


}
