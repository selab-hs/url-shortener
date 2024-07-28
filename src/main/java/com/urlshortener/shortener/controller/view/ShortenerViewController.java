package com.urlshortener.shortener.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ShortenerViewController {
    /**
     * 메인 페이지 접속
     *
     * @return 메인 화면을 반환합니다.
     */
    @GetMapping("/main")
    public String page() {
        return "main_page";
    }

    /**
     * 메인 페이지 접속
     *
     * @return 메인 화면을 반환합니다.
     */
    @GetMapping("/")
    public String homePage() {
        return "main_page";
    }

    /**
     * 회원 페이지 접속
     *
     * @return 회원이 사용하는 화면을 반환합니다.
     */
    @GetMapping("/member_home")
    public String sidePage() {
        return "main_member_page";
    }
}