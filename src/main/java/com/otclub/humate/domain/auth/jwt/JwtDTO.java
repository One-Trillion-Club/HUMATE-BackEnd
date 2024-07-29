package com.otclub.humate.domain.auth.jwt;

import lombok.Builder;

@Builder
public record JwtDTO(
        String accessToken,
        String refreshToken
        ) {}
