package com.otclub.humate.domain.companion.mapper;

import com.otclub.humate.common.entity.Companion;
import com.otclub.humate.domain.companion.dto.CompanionDetailsDTO;
import com.otclub.humate.domain.companion.dto.CompanionPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CompanionMapper {
    int updateCompanionStatusById(int companionId);

    Optional<CompanionPostDTO> selectCompanionAndPost(
            @Param("companionId") int companionId,
            @Param("memberId") String memberId);

    Optional<Companion> selectCompanionByIds(
            @Param("companionId") int companionId,
            @Param("memberId") String memberId);

    List<CompanionDetailsDTO> selectCompanionListByMemberId(String memberId);
}
