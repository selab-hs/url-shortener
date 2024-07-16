package com.urlshortener.actionlog.controller.view;

import com.urlshortener.actionlog.application.SystemActionLogService;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class SystemActionLogViewController {

    private final SystemActionLogService systemActionLogService;

    @GetMapping("/history/{shortCode}")
    public String getSystemActionLogViewHome(@PathVariable("shortCode") ShortCodeRequest shortCode, Model model) {
        var logs = systemActionLogService.getAllShortcodeViews(shortCode);
        model.addAttribute("logs", logs);

        return "system_action_log_board";
    }

    @GetMapping("/logs")
    public String shortcodeTable() {

        return "access_shortcode_page";
    }
}