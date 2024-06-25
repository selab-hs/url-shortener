package org.service.urlshortener.shortener.viewcontroller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.shortener.dto.request.LongUrlRequest;
import org.service.urlshortener.shortener.dto.request.ShortUrlRequest;
import org.service.urlshortener.shortener.service.ShortenerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShortenerViewController {

    private final ShortenerService service;
    public static final String URL = "http://localhost:8080/";

    @GetMapping("/main")
    public String home(){
        return "home";
    }

    @PostMapping("/shortener")
    public String createShortenerUrl(@RequestParam("origin url") String originUrl, Model model){
        var shortUrl = service.createShortUrl(new LongUrlRequest(originUrl));
        model.addAttribute("shortUrl",URL+ shortUrl.getShortUrl());

        return "home";
    }

    @GetMapping("/{shortUrl}")
    public String goOriginUrl(@PathVariable("shortUrl") String shortUrl){
        var originUrl = service.getOriginUrl(new ShortUrlRequest(shortUrl.replace(URL, ""))).getOriginUrl();

        return "redirect:"+originUrl;
    }
}
