package com.otclub.humate.domain.companion.mapper;

import com.otclub.humate.common.entity.Companion;
import com.otclub.humate.domain.activity.dto.CompanionInfoDTO;
import com.otclub.humate.domain.companion.dto.CompanionDTO;
import com.otclub.humate.domain.companion.dto.CompanionDetailsDTO;
import com.otclub.humate.domain.companion.dto.CompanionPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 동행 Mapper
 * @author 손승완
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	손승완        최초 생성
 * </pre>
 */
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

    CompanionInfoDTO selectPostTitleById(int companionId);

    int countCompanionByMemberIdAndCompanionId(@Param("memberId") String memberId,
                                               @Param("companionId") int companionId);

    int insertCompanion(CompanionDTO companionDTO);
}
