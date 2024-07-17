package com.urlshortener.member.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MemberController {
    /**
     * @return 로그인 화면을 반환합니다.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    /**
     * @return 회원가입 화면을 반환합니다.
     */
    @GetMapping("/sign-up")
    public String signUpPage() {
        return "sign_up_page";
    }
}