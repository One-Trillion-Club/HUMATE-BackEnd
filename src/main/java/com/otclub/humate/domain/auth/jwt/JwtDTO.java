package com.otclub.humate.domain.auth.jwt;

/**
 * JWT 액세스 토큰과 리프레시 토큰을 담는 DTO
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
public record JwtDTO(
        String accessToken,
        String refreshToken
        ) {}
