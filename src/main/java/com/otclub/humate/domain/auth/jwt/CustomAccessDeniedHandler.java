//package com.otclub.humate.domain.auth.jwt;
//
//import com.otclub.humate.common.exception.ErrorCode;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
//            throws IOException {
//
//        // 토큰 인증 후 권한 거부
//        ErrorCode errorCode = ErrorCode.FORBIDDEN_REQUEST;
//        JwtAuthenticationFilter.setErrorResponse(response, errorCode);
//    }
//}
