package com.urlshortener.member.controller.rest;

import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.member.domain.dto.request.JoinMemberRequest;
import com.urlshortener.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 정보 관리")
@Slf4j
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberRestController {
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

    /**
     * @param email : 회원가입 중복확인을 위한 email String
     * @return boolean 값 리턴. 실패했다면 Exception 발행
     */
    @Operation(summary = "중복 이메일 확인", description = "회원 가입 시 회원 이메일 중복 여부를 확인합니다.", tags = {"members"})
    @GetMapping("/email-check")
    public ResponseEntity<?> checkEmailForSignUp(@RequestParam String email) {
        memberService.duplicateValidationMemberEmail(email);

        return ResponseDto.ok(true);
    }
}
