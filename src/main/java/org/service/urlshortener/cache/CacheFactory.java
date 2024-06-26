package org.service.urlshortener.cache;

import org.service.urlshortener.shortener.dto.ShortUrlModel;

import java.time.Duration;

public class CacheFactory {
    public static Cache<ShortUrlModel> makeCachedQuiz(Long id){
        return new Cache<>(
                "url:short:"+id,
                ShortUrlModel.class,
                Duration.ofMinutes(60)
        );
    }
}