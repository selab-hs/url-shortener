package com.urlshortener.actionlog.controller.view;

import com.urlshortener.actionlog.application.SystemActionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SystemActionLogViewController {

    private final SystemActionLogService systemActionLogService;

    /**
     * 유저의 시스템 액션 로그 게시판 페이지 접속
     *
     * @return 시스템 액션 로그 게시판 페이지를 반환합니다.
     */
    @GetMapping("/history/{shortCode}")
    public String getSystemActionLogViewHome() {
        return "system_action_log_board_page";
    }

    /**
     * 유저가 생성한 Short Code 페이지 접속
     *
     * @return 생성된 Short Code 페이지를 반환합니다.
     */
    @GetMapping("/logs")
    public String shortcodeTable() {
        return "access_shortcode_page";
    }
}