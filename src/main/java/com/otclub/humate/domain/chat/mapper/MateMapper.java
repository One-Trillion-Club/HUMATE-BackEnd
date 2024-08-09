package com.otclub.humate.domain.chat.mapper;


import com.otclub.humate.domain.chat.dto.MateUpdateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 메이트 Mapper
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
@Mapper
public interface MateMapper {
    // 메이트 맺기 신청/취소
    int updateMate(MateUpdateRequestDTO requestDTO);

}
