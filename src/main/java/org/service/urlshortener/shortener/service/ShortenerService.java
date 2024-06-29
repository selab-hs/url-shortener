package org.service.urlshortener.shortener.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.service.urlshortener.error.dto.ErrorMessage;
import org.service.urlshortener.error.exception.url.NotFoundUrlException;
import org.service.urlshortener.shortener.domain.OriginUrl;
import org.service.urlshortener.shortener.dto.request.OriginUrlRequest;
import org.service.urlshortener.shortener.dto.request.ShortCodeRequest;
import org.service.urlshortener.shortener.dto.response.OriginUrlResponse;
import org.service.urlshortener.shortener.dto.response.ShortCodeResponse;
import org.service.urlshortener.shortener.repository.OriginUrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortenerService {
    private final OriginUrlRepository originUrlRepository;
    private final EncryptionService encryptionService;

    /**
     * @param request 는 변경 할 OriginUrl
     * @return originUrl -> shortUrl 로 변환 값을 'ShortUrlResponse' 로 반환합니다.
     */
    @Transactional
    public ShortCodeResponse createShortUrl(OriginUrlRequest request) {
        if (originUrlRepository.existsByOriginUrl(request.getOriginUrl())) {
            Long id = originUrlRepository.findByOriginUrl(request.getOriginUrl()).get().getId();
            return new ShortCodeResponse(encryptionService.encode(id));
        }
        OriginUrl url = originUrlRepository.save(new OriginUrl(request.getOriginUrl()));

        return new ShortCodeResponse(encryptionService.encode(url.getId()));
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
        var originUrl = originUrlRepository.findById(originUrlId)
                .orElseThrow(() -> new NotFoundUrlException(ErrorMessage.NOT_FOUND_URL));

        return new OriginUrlResponse(originUrl.getOriginUrl());
    }
}