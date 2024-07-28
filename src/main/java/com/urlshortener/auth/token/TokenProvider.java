package com.urlshortener.auth.token;

import com.urlshortener.auth.model.AuthToken;
import com.urlshortener.auth.model.AuthUser;
import com.urlshortener.auth.model.AuthUserImpl;
import com.urlshortener.auth.service.AuthService;
import com.urlshortener.error.dto.ErrorMessage;
import com.urlshortener.error.exception.auth.ReadysExpiredJwtException;
import com.urlshortener.error.exception.auth.ReadysJwtException;
import com.urlshortener.util.DateUtil;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "roles";

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expiry}")
    private Long tokenExpiry;

    @Value("${jwt.token.refresh-expiry}")
    private Long refreshTokenExpiry;

    private final AuthService authService;

    public TokenProvider(@Lazy AuthService authService) {
        this.authService = authService;
    }

    @PostConstruct
    public void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * @param * claim : 데이터 (String userId)
     *          * Subject : 토큰 생성 목적
     *          * IssuedAt : jwt 발급 시간
     *          * Expiration Time : jwt 만료시간
     *          * signWith : 암호화 알고리즘, secret 값 세팅
     *          *
     * @return * access Jwt Token
     * @brief * 토큰 생성 / jjwt 라이브러리
     */
    public String generateJwtToken(Long userId, String role) {
        var claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put(AUTHORITIES_KEY, role);
        var date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(DateUtil.getTokenValidTime(date, tokenExpiry))
                .compact();
    }

    /**
     * @param * getUserToken : 파싱된 토큰
     * @return * Authentication : 유저 정보와 유저 역할
     * @brief * 토큰 인증 정보 조회
     */
    public AuthUser getAuthMember(AuthToken token) {
        var id = getUserToken(token.getToken());
        var member = authService.loadUserById(id);

        return new AuthUserImpl(member.getId());
    }

    /**
     * @param * setSigningKey : jwt key
     *          * parseClaimsJws : 파싱할 token
     *          * Body : claims
     *          * Subject : userId
     * @return * Long userId
     * @brief * 토큰 파싱 (해석) / jjwt 라이브러리
     */
    public Long getUserToken(String token) {
        var data = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return Long.parseLong(data);
    }

    /**
     * @param * setSigningKey : jwt key
     *          * parseClaimsJws : 파싱할 token
     * @return * token 기간
     * @brief * 토큰 기간 유효성 검사
     */

    public boolean validateDateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration().after(new Date());
        } catch (ExpiredJwtException e) {
            throw new ReadysExpiredJwtException(ErrorMessage.EXPIRED_JWT_EXCEPTION);
        } catch (JwtException e) {
            throw new ReadysJwtException(ErrorMessage.WRONG_JWT_EXCEPTION);
        }
    }
}