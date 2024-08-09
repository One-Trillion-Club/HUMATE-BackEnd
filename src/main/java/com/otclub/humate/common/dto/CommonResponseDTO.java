package com.otclub.humate.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 성공 여부, 메시지를 반환하는 Response DTO
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
@AllArgsConstructor
public class CommonResponseDTO {

    private boolean success;
    private String message;
}
