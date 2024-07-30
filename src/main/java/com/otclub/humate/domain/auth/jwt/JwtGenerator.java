package com.otclub.humate.domain.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import static com.otclub.humate.domain.auth.constant.AuthConstant.*;

/**
 * JWT 토큰 생성 클래스
 * @author 조영욱
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	조영욱        최초 생성
 * </pre>
 */
@Component
public class JwtGenerator {

    private final Key key;

    /**
     * 생성자: 설정 파일을 통해 key 초기화
     *
     * @author 조영욱
     * @param secretKey BASE64로 인코딩된 서버 비밀키
     */
    public JwtGenerator(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Member 정보를 통해 AccessToken, RefreshToken 생성
     *
     * @author 조영욱
     * @param memberId
     */
    public JwtDTO generateToken(String memberId) {
        long now = (new Date()).getTime();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(memberId)
                .claim("auth", "MEMBER") // todo: admin 권한 처리
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new JwtDTO(accessToken, refreshToken);
    }
}
