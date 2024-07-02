package com.urlshortener.shortener.comtroller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ShortenerViewController {
    /**
     * @return 메인 화면을 반환합니다.
     */
    @GetMapping("/main")
    public String page() {
        return "main";
    }

    /**
     * @return 메인 화면을 반환합니다.
     */
    @GetMapping("/")
    public String homePage() {
        return "main";
    }
}