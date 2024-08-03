package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.MateUpdateRequestDTO;

/**
 * 메이트 관련 서비스
 * @author 최유경
 * @since 2024.08.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  	최유경        최초 생성
 * </pre>
 */
public interface MateService {
    void modifyMate(MateUpdateRequestDTO requestDTO) ;
}
