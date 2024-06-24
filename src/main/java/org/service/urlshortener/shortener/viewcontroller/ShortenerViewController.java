package org.service.urlshortener.shortener.viewcontroller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.shortener.dto.request.LongUrlRequest;
import org.service.urlshortener.shortener.dto.request.ShortUrlRequest;
import org.service.urlshortener.shortener.service.ShortenerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShortenerViewController {

    private final ShortenerService service;

    @GetMapping("/main")
    public String home(){
        return "home";
    }

    @PostMapping("/shortener")
    public String createShortenerUrl(@RequestParam("origin url") String originUrl, Model model){
        var shortUrl = service.createShortUrl(new LongUrlRequest(originUrl));
        model.addAttribute("shortUrl", shortUrl.getShortUrl());

        return "home";
    }

    @GetMapping("/origin")
    public String getOriginUrl(@RequestParam("shortUrl") String shortUrl){
        var originUrl = service.getOriginUrl(new ShortUrlRequest(shortUrl));
        log.info("origin={}", originUrl.getOriginUrl());
        return "redirect:"+originUrl.getOriginUrl();
    }
}
