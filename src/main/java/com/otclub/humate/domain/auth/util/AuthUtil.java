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
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 600_0L; // 10분 (millisecond)
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 604_800_000L; // 1주 (millisecond)

    // JWT Cookie
    public static final int JWT_TOKEN_COOKIE_MAX_AGE = 604_800; // 1주 (second)

    public static Cookie createJwtTokenCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(JWT_TOKEN_COOKIE_MAX_AGE);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }
}
