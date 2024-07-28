package com.otclub.humate.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_KEY(409, "중복되는 아이디나 닉네임이 존재합니다. 다시 시도해주세요.");

    private final int status;
    private final String message;
}
