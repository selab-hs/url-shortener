package org.service.urlshortener.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JspHealthController {

    @GetMapping("/health")
    public String home(){
        return "index";
    }
}
