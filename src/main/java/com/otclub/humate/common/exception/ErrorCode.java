package com.otclub.humate.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_KEY(409, "중복되는 아이디나 닉네임이 존재합니다. 다시 시도해주세요."),
    NOT_VALID_USER_INFORMATION(400, "아이디나 비밀번호가 올바르지 않습니다.");

    private final int status;
    private final String message;
}
