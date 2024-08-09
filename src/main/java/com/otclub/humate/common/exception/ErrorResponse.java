package com.otclub.humate.common.exception;


import lombok.Builder;
import lombok.Getter;

/**
 * Error Response Class 생성
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30   손승완        최초 생성
 * </pre>
 */
@Getter
@Builder
public class ErrorResponse {
    private final int status;
    private final String errorCode;
    private final String message;
}
