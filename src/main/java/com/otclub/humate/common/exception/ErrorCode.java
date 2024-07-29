package com.otclub.humate.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_KEY(409, "중복되는 아이디나 닉네임이 존재합니다. 다시 시도해주세요."),
    UPLOAD_FAIL(400, "사진 업로드에 실패했습니다."),
    ALREADY_EXISTS_ACTIVITY(409, "이미 존재하는 활동입니다. 다른 활동을 등록해 주세요.");

    private final int status;
    private final String message;
    }
