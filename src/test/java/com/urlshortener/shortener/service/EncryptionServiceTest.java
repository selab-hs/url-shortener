package com.urlshortener.shortener.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Slf4j
class EncryptionServiceTest {

    @InjectMocks
    private EncryptionService encryptionService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        // BASE62 필드를 모킹된 값으로 설정합니다.
        //스코프
        ReflectionTestUtils.setField(encryptionService,
                "BASE62",
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }

    @Test
    @DisplayName("인코딩 테스트")
    public void testEncode() {
        String encoded = encryptionService.encode(12345);
        assertThat(encoded).isEqualTo("dnh");
    }

    @Test
    @DisplayName("디코드 테스트")
    public void testDecode() {
        long decoded = encryptionService.decode("dnh");
        assertThat(decoded).isEqualTo(12345);
    }
}