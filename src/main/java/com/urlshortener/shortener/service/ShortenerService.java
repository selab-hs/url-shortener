package com.urlshortener.shortener.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortenerService {
    private final ShortUrlRepository originUrlRepository;
    private final CacheService cacheService;
    private final EncryptionService encryptionService;

    @Value("${server.domain}")
    private String domain;

    /**
     * @param request 는 변경 할 OriginUrl
     * @return originUrl -> shortUrl 로 변환 값을 'ShortUrlResponse' 로 반환합니다.
     */
    @Transactional
    public ShortCodeResponse createShortUrl(OriginUrlRequest request) {
        ShortUrl url = originUrlRepository.save(ShortUrl.from(request.getOriginUrl()));
        cacheService
                .asyncSet(CacheFactory
                                .makeCachedQuiz(
                                        url.getId()),
                        ShortUrlModel.from(
                                url.getId(),
                                url.getOriginUrl(),
                                url.getCreatedAt()));
        var shortCode = encryptionService.encode(url.getId());

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
        var cache = CacheFactory.makeCachedQuiz(originUrlId);
        var resultUrl = cacheService.get(cache, () -> {
            var findUrl = originUrlRepository
                    .findById(originUrlId)
                    .orElseThrow(() -> new NotFoundUrlException(ErrorMessage.NOT_FOUND_URL));

            return ShortUrlModel.from(
                    findUrl.getId(),
                    findUrl.getOriginUrl(),
                    findUrl.getCreatedAt());
        }).getOriginalUrl();

        return OriginUrlResponse.from(resultUrl);
    }
}