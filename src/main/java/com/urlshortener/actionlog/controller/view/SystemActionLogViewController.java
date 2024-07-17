package com.urlshortener.actionlog.controller.view;

import com.urlshortener.actionlog.application.SystemActionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SystemActionLogViewController {

    private final SystemActionLogService systemActionLogService;

    @GetMapping("/history/{shortCode}")
    public String getSystemActionLogViewHome() {
        return "system_action_log_board_page";
    }

    @GetMapping("/logs")
    public String shortcodeTable() {
        return "access_shortcode_page";
    }
}