package com.urlshortener.health.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewHealthController {
    @GetMapping("/health")
    public String home() {
        return "index";
    }
}
