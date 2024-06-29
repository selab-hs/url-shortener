package org.service.urlshortener.shortener.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.service.urlshortener.common.entity.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OriginUrl extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_generator")
    @SequenceGenerator(name = "url_generator", sequenceName = "url_id", initialValue = 20000, allocationSize = 1)
    private Long id;

    @Column(name = "origin_url")
    private String originUrl;

    public OriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }
}