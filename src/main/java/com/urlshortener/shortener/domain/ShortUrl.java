package com.urlshortener.shortener.domain;

import com.urlshortener.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "idx_member_id", columnList = "member_id"))
public class ShortUrl extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "short_url_generator")
    @SequenceGenerator(name = "short_url_generator", sequenceName = "id", initialValue = 20000, allocationSize = 1)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    /**
     * 요청한 url
     */
    @Column(name = "origin_url")
    private String originUrl;

    /**
     * ShortUrl Entity 생성 method
     *
     * @param originUrl
     * @return ShortUrl
     */
    public static ShortUrl from(String originUrl, Long memberId) {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.memberId = memberId;
        shortUrl.originUrl = originUrl;

        return shortUrl;
    }
}