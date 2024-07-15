package com.urlshortener.auth.controller.rest;

import com.urlshortener.auth.model.AuthUser;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth/dev")
public class AuthDevRestController {
    /**
     * AuthUser를 통해 인증된 사용자만을 넘긴다, 만약 인증이 되지 않은 경우에는 에러가 발생
     */
    @GetMapping("/test1")
    public String test1(AuthUser user) {
        return "OK";
    }

    /**
     * AuthUser를 통해 인증된 사용자만을 넘긴다, 단, 토큰이 없이 요청된 사용자의 경우에는 인증이 없어도 사용 가능하다.
     * - 토큰을 넘기는 경우, 인증인가 체크
     * - 토큰을 넘기지 않는 경우, 인증인가 체크를 진행하지 않는다.
     */
    @GetMapping("/test2")
    public String test2(@Nullable AuthUser user) {
        return "OK";
    }
}
