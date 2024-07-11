package com.urlshortener.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(indexes = @Index(name = "idx_uuid", columnList = "uuid"))
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 클라이언트 유저 uuid
     */
    @Column(nullable = false)
    private String uuid;

    /**
     * Member Entity 생성 builder
     *
     * @param uuid : Client UUID
     * @return ShortUrl
     * @prarm memberType : user MemberType
     */
    @Builder
    public Member(String uuid) {
        this.uuid = uuid;
    }
}
