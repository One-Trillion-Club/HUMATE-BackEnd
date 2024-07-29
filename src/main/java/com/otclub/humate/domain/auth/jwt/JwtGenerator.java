package com.otclub.humate.domain.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {

    private final Key key;
    private final int accessTokenExpireTime;
    private final int refreshTokenExpireTime;

    public JwtGenerator(@Value("${jwt.secret}") String secretKey,
                        @Value("${jwt.accessTokenExpireTime}") String accessTokenExpireTime,
                        @Value("${jwt.refreshTokenExpireTime}") String refreshTokenExpireTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpireTime = Integer.parseInt(accessTokenExpireTime);
        this.refreshTokenExpireTime = Integer.parseInt(refreshTokenExpireTime);
    }

    // Member 정보를 가지고 AccessToken, RefreshToken을 생성하는 메서드
    public JwtDTO generateToken(String memberId) {
        long now = (new Date()).getTime();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(memberId)
//                .claim("auth", authorities)
                .setExpiration(new Date(now + accessTokenExpireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + refreshTokenExpireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
