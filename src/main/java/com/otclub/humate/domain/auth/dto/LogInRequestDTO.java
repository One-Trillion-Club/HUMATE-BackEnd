package com.otclub.humate.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 로그인 Request DTO
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
@Getter
@Setter
public class LogInRequestDTO {

    private String loginId;
    private String password;
}
