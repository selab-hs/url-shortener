package com.urlshortener.member.domain;

import com.urlshortener.common.entity.BaseEntity;
import com.urlshortener.member.domain.converter.PasswordEncodeConverter;
import com.urlshortener.member.domain.vo.Email;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member", indexes = @Index(name = "uidx__email", columnList = "email"))
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated
    private Email email;

    @Column(nullable = false)
    @Convert(converter = PasswordEncodeConverter.class)
    private String password;

    public Member(String email, String password) {
        this.email = new Email(email);
        this.password = password;
    }
}
