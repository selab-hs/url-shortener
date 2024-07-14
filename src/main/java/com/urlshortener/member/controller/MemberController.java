package com.urlshortener.member.controller;

import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.member.domain.dto.request.JoinMemberRequest;
import com.urlshortener.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 가입 API
     *
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody JoinMemberRequest request) {
        var member = memberService.createMember(request);

        return ResponseDto.created(member);
    }
}
