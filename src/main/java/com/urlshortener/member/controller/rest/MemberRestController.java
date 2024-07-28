package com.urlshortener.member.controller.rest;

import com.urlshortener.common.response.ResponseDto;
import com.urlshortener.member.domain.dto.request.JoinMemberRequest;
import com.urlshortener.member.domain.dto.response.MemberResponseDto;
import com.urlshortener.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
     * @param request     회원 가입 email, password
     * @param httpRequest cookie uuid 조회 및 요청 액션 로그 저장을 위한 Servlet Request
     * @return member : 생성된 memberId 및 email
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "멤버 회원 가입 성공",
            content = @Content(schema = @Schema(implementation = MemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 회원 가입 정보")
    })
    @PostMapping
    public ResponseEntity<?> createMember(
            @RequestBody JoinMemberRequest request,
            HttpServletRequest httpRequest) {
        var member = memberService.createMember(request, httpRequest);

        return ResponseDto.created(member);
    }

    /**
     * 회원가입 Email 중복 확인
     *
     * @param email : 회원가입 중복확인을 위한 email String
     * @return true 리턴. 실패했다면 Exception 발행
     */
    @Operation(summary = "중복 이메일 확인", description = "회원 가입 시 회원 이메일 중복 여부를 확인", tags = {"members"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "이메일 중복 검사 성공"),
            @ApiResponse(responseCode = "400", description = "이미 존재하는 Email")
    })
    @GetMapping("/email-check")
    public ResponseEntity<?> checkEmailForSignUp(@RequestParam String email) {
        memberService.duplicateValidationMemberEmail(email);

        return ResponseDto.ok(true);
    }
}