package com.urlshortener.actionlog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShortcodeViewCountResponse {
    private Long viewCount;

    public static ShortcodeViewCountResponse from(Long viewCount) {
        return new ShortcodeViewCountResponse(viewCount);
    }
}
