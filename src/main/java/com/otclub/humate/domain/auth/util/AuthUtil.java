package com.otclub.humate.domain.auth.util;

import jakarta.servlet.http.Cookie;

/**
 * Auth 모듈에서 사용할 상수 클래스
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
public class AuthUtil {
    // JWT Token
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 600_000L; // 10분 (millisecond)
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 604_800_000L; // 1주 (millisecond)

    // JWT Cookie
    public static final int JWT_TOKEN_COOKIE_MAX_AGE = 604_800; // 1주 (second)

    // Redis
    public static final int REDIS_VERIFICATION_SUCCESS_KEY_TTL = 3600; // 1시간
    public static final int REDIS_CODEF_ACCESS_TOKEN_TTL = 604800; // 1주
    public static final int REDIS_PHONE_CODE_KEY_TTL = 18000; // 5분

    /**
     * JWT 쿠키 생성
     *
     * @author 조영욱
     * @param key 쿠키 key
     * @param value 쿠키 value
     * @return 쿠키
     */
    public static Cookie createJwtTokenCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(JWT_TOKEN_COOKIE_MAX_AGE);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }

    /**
     * JWT 쿠키 삭제를 위한 쿠키 생성
     *
     * @author 조영욱
     * @param key 쿠키 key
     */
    public static Cookie createJwtTokenDeleteCookie(String key) {
        Cookie cookie = new Cookie(key, "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }
}
