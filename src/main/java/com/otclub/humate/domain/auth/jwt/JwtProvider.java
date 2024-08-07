package com.otclub.humate.domain.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;

/**
 * JWT 토큰 프로바이더 클래스
 * @author 조영욱
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	조영욱        최초 생성
 * </pre>
 */
@Slf4j
@Component
public class JwtProvider {

    private final Key key;

    /**
     * 생성자: 설정 파일을 통해 key 초기화
     *
     * @author 조영욱
     * @param secretKey BASE64로 인코딩된 서버 비밀키
     */
    @Autowired
    public JwtProvider(@Value("${jwt.secret}") String secretKey, JwtGenerator jwtGenerator) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * JWT 토큰을 통해 Authentication 객체 반환
     *
     * @author 조영욱
     * @param token
     * @return Authentication
     * @exception RuntimeException
     */
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져옴
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        // UserDetails 객체를 생성해 Authentication 반환
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * 토큰 정보 검증
     *
     * @author 조영욱
     * @param token
     * @exception  ExpiredJwtException, AccessDeniedException
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token");
        } catch (ExpiredJwtException expiredJwtException) {
            log.info("Expired JWT Token\n**Refresh Access Token required");
            throw expiredJwtException; // 토큰 만료 시 따로 만료 예외 throw (리프레시 로직으로 이동을 위함)
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.");
        }

        throw new AccessDeniedException("Access denied");
    }


    /**
     * JWT 페이로드의 클레임 파싱하여 반환
     *
     * @author 조영욱
     * @param token
     * @return Claims
     */
    public Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
