package com.urlshortener.shortener.service;

import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.cache.CacheFactory;
import com.urlshortener.cache.CacheService;
import com.urlshortener.shortener.domain.ShortUrl;
import com.urlshortener.shortener.dto.model.ShortUrlModel;
import com.urlshortener.shortener.dto.request.OriginUrlRequest;
import com.urlshortener.shortener.dto.response.ShortCodeResponse;
import com.urlshortener.shortener.repository.ShortUrlRepository;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DevShortenerService {
    private final ShortUrlRepository shortUrlRepository;
    private final CacheService cacheService;
    private final EncryptionService encryptionService;

    @Value("${server.domain}")
    private String domain;

    /**
     * @param request 는 변경 할 OriginUrl
     * @return originUrl -> shortUrl 로 변환 값을 'ShortUrlResponse' 로 반환합니다.
     */
    @Transactional
    public ShortCodeResponse createShortUrl(
            @Nullable AuthUser user,
            OriginUrlRequest request,
            HttpServletRequest httpServletRequest
    ) {
        Long memberId = AuthUser.resolveMemberId(user);

        ShortUrl createdShortUrl = shortUrlRepository.save(
                ShortUrl.from(
                        request.getOriginUrl(),
                        memberId,
                        UUID.randomUUID().toString()
                )
        );

        cacheService.asyncSet(
                CacheFactory.makeShortUrl(createdShortUrl.getId()),
                ShortUrlModel.from(memberId, createdShortUrl)
        );

        var shortCode = encryptionService.encode(createdShortUrl.getId());

        return ShortCodeResponse.from(domain + shortCode);
    }
}