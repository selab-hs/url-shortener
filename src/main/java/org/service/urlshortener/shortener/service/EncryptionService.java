package org.service.urlshortener.shortener.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionService {
    @Value("${security.base62}")
    private String BASE62;

    /**
     * Base62 로 엔코드
     *
     * @param index - db 인덱스
     * @return String type으로 암호한 code 반환
     */
    public String encode(long index) {
        StringBuilder sb = new StringBuilder();
        while (index > 0) {
            sb.append(BASE62.charAt((int) (index % 62)));
            index /= 62;
        }
        return sb.reverse().toString();
    }

    /**
     * Base62 로 디코드
     *
     * @param code
     * @return long type으로 db 인덱스 반환
     */
    public long decode(String code) {
        long result = 0;
        long power = 1;
        for (int i = code.length() - 1; i >= 0; i--) {
            int index = BASE62.indexOf(code.charAt(i));
            result += index * power;
            power *= 62;
        }
        return result;
    }
}