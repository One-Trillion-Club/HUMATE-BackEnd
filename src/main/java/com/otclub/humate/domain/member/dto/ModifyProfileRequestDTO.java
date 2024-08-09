package com.otclub.humate.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 프로필 정보 수정 Request DTO
 * @author 조영욱
 * @since 2024.08.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.04  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
public class ModifyProfileRequestDTO {
    private String nickname;
    private String introduction;
    private String profileImgUrl;
}
