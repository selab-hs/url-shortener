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
        @Index(name = "idx__member_id__short_url_id", columnList = "member_id, short_url_id")
})
public class SystemActionLog extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "short_url_id")
    private Long urlId;

    private String host;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "ip_address")
    private String ipAddress;

    private String path;

    private String referer;

    @Column(name = "user_agent")
    private String userAgent;

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

    public void updateMember(Long memberId) {
        this.memberId = memberId;
    }
}
