package com.otclub.humate.domain.companion.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanionMapper {
    int updateCompanionStatusById(int companionId);
}
