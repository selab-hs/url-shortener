package com.urlshortener.shortener.service;

import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.cache.CacheFactory;
import com.urlshortener.cache.CacheService;
import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.url.NotFoundUrlException;
import com.urlshortener.shortener.domain.ShortUrl;
import com.urlshortener.shortener.dto.model.ShortUrlModel;
import com.urlshortener.shortener.dto.request.OriginUrlRequest;
import com.urlshortener.shortener.dto.request.ShortCodeRequest;
import com.urlshortener.shortener.dto.response.OriginUrlResponse;
import com.urlshortener.shortener.dto.response.ShortCodeResponse;
import com.urlshortener.shortener.repository.ShortUrlRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortenerService {
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
    public ShortCodeResponse createShortUrl(@Nullable AuthUser user, OriginUrlRequest request) {
        Long memberId = AuthUser.resolveMemberId(user);

        ShortUrl createdShortUrl = shortUrlRepository.save(ShortUrl.from(request.getOriginUrl(), memberId));

        cacheService.asyncSet(
                CacheFactory.makeCachedQuiz(createdShortUrl.getId()),
                ShortUrlModel.from(memberId, createdShortUrl)
        );

        var shortCode = encryptionService.encode(createdShortUrl.getId());

        return ShortCodeResponse.from(domain + shortCode);
    }

    /**
     * shortUrl -> originUrl 변화는 서비스
     *
     * @param request 는 리다이렉드 할 shortUrl
     * @return OriginUrlResp
     * @throws NotFoundUrlException 유효하지 않은 shotUrl 이 요청 되었을 경우
     */
    @Transactional(readOnly = true)
    public OriginUrlResponse getOriginUrl(ShortCodeRequest request) {
        var originUrlId = encryptionService.decode(request.getShortCode());

        var resultUrl = getShortUrl(originUrlId);

        return OriginUrlResponse.from(resultUrl);
    }

    public ShortUrlModel getShortUrl(long originUrlId) {
        return cacheService.get(CacheFactory.makeCachedQuiz(originUrlId), () -> {
            var shortUrl = shortUrlRepository
                    .findById(originUrlId)
                    .orElseThrow(() -> new NotFoundUrlException(ErrorMessage.NOT_FOUND_URL));

            return ShortUrlModel.from(shortUrl);
        });
    }
}
