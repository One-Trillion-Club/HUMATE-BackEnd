package com.otclub.humate.common.exception;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final int status;
    private final String errorCode;
    private final String message;
}
