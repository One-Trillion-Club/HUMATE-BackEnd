package com.otclub.humate.domain.companion.mapper;

import com.otclub.humate.domain.companion.dto.CompanionPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface CompanionMapper {
    int updateCompanionStatusById(int companionId);
    Optional<CompanionPostDTO> selectCompanionAndPost(int companionId);

}
