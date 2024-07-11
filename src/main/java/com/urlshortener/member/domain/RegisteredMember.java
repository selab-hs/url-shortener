package com.urlshortener.member.domain;

import com.urlshortener.common.entity.BaseEntity;
import com.urlshortener.member.domain.converter.PasswordEncodeConverter;
import com.urlshortener.member.domain.vo.Email;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(indexes = {
        @Index(name = "idx_member_email", columnList = "email"),
        @Index(name = "idx_registered_member_id", columnList = "member_id")})
public class RegisteredMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(nullable = false, unique = true)
    @Enumerated
    private Email email;

    @Column(nullable = false)
    @Convert(converter = PasswordEncodeConverter.class)
    private String password;

    @Builder
    public RegisteredMember(Long memberId, String email, String password) {
        this.memberId = memberId;
        this.email = new Email(email);
        this.password = password;
    }
}
