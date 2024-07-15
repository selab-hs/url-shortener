package com.urlshortener.actionlog.domain;

import com.urlshortener.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "system_action_log", indexes = {
        @Index(name = "idx_url_id", columnList = "shorturl_idx")
})
public class SystemActionLog extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "shorturl_idx")
    private Long urlId;

    private String path;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "user_agent")
    private String userAgent;

    private String host;

    private String referer;

    public SystemActionLog(
            String ipAddress,
            String path,
            Long urlId,
            String httpMethod,
            String userAgent,
            String host,
            String referer
    ) {
        this.ipAddress = ipAddress;
        this.path = path;
        this.urlId = urlId;
        this.httpMethod = httpMethod;
        this.userAgent = userAgent;
        this.host = host;
        this.referer = referer;
    }
}
