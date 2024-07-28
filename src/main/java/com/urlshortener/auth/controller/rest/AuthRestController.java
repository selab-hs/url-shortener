package com.urlshortener.auth.controller.rest;

import com.urlshortener.auth.dto.JoinRequest;
import com.urlshortener.auth.dto.UserInfoResponse;
import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.auth.service.AuthService;
import com.urlshortener.common.response.ResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    /**
     * 로그인 API
     *
     * @param request 로그인 email, password
     * @return String 유저 JWT 토큰 반환
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "멤버 로그인 성공 및 토큰 반환",
            content = @Content(schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "해당 유저가 존재하지 않음"),
            @ApiResponse(responseCode = "400", description = "비밀번호가 맞지 않음"),
            @ApiResponse(responseCode = "403", description = "토큰 만료"),
            @ApiResponse(responseCode = "500", description = "JWT 토큰 생성 실패")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JoinRequest request) {
        var userDetail = authService.userLogin(request);

        return ResponseDto.created(userDetail);
    }

    /**
     * 유저 정보 가져오기 API
     *
     * @param user 로그인된 유저 ID
     * @return response 유저 정보 Response
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "유저 정보 가져오기 성공",
            content = @Content(schema = @Schema(implementation = UserInfoResponse.class))),
            @ApiResponse(responseCode = "404", description = "해당 유저가 존재하지 않음"),
    })
    @GetMapping("/info")
    public ResponseEntity<?> getInformation(AuthUser user) {
        var response = authService.loadUserById(user.getId());

        return ResponseDto.ok(response);
    }
}