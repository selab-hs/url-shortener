package com.urlshortener.auth.model;

import jakarta.annotation.Nullable;

/**
 * Toks Auth User
 */
public interface AuthUser {
    Long getId();

    /**
     * 인증되지 않은 사용자에 대한 처리
     */
    static Long resolveMemberId(@Nullable AuthUser user) {
        if (user == null) {
            return -1L;
        }

        return user.getId();
    }
}