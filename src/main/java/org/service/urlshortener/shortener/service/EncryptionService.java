package org.service.urlshortener.shortener.service;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class EncryptionService {

    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String hashLong(long value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(longToBytes(value));
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash data", e);
        }
    }

    private byte[] longToBytes(long value) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte)(value & 0xFF);
            value >>= 8;
        }
        return result;
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.substring(0, 10);
    }

    public String encode(String stringValue) {
        BigInteger value = new BigInteger(stringValue,16);
        StringBuilder encoded = new StringBuilder();

        if (value.equals(BigInteger.ZERO)) {
            return String.valueOf(BASE62.charAt(0));
        }

        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] quotientAndRemainder = value.divideAndRemainder(BigInteger.valueOf(62));
            int remainder = quotientAndRemainder[1].intValue();
            encoded.append(BASE62.charAt(remainder));
            value = quotientAndRemainder[0];
        }

        return encoded.reverse().toString();
    }

    public String decode(String encodedValue) {
        BigInteger value = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(62);

        // Base62 인코딩된 문자열을 디코딩
        for (int i = 0; i < encodedValue.length(); i++) {
            int index = BASE62.indexOf(encodedValue.charAt(i));
            value = value.multiply(base).add(BigInteger.valueOf(index));
        }

        // 결과값을 16진수 문자열로 변환
        return value.toString(16);
    }
}
