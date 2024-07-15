package com.urlshortener.shortener.domain;

import com.urlshortener.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "short_url", indexes = @Index(name = "idx__member_id", columnList = "member_id"))
public class ShortUrl extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return ShortUrl.builder()
                .memberId(memberId)
                .originUrl(originUrl)
                .build();
    }
}
