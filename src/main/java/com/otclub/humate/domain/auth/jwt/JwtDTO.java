package com.otclub.humate.domain.auth.jwt;

public record JwtDTO(
        String accessToken,
        String refreshToken
        ) {}
