package com.otclub.humate.domain.chat.mapper;


import com.otclub.humate.domain.chat.dto.MateCreateRequestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MateMapper {
    int updateMate(MateCreateRequestDTO requestDTO);

}
