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
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * </pre>
 */
@Mapper
public interface CompanionMapper {
    // 동행 상태 변경
    int updateCompanionStatusById(int companionId);

    // 동행 매칭 정보 조회
    Optional<CompanionPostDTO> selectCompanionAndPost(
            @Param("companionId") int companionId,
            @Param("memberId") String memberId);

    // 동행 정보 조회
    Optional<Companion> selectCompanionByIds(
            @Param("companionId") int companionId,
            @Param("memberId") String memberId);

    // 동행 목록 조회
    List<CompanionDetailsDTO> selectCompanionListByMemberId(String memberId);

    // 동행 id로 매칭 제목 조회
    CompanionInfoDTO selectPostTitleById(int companionId);

    // 해당 동행에 대해 참여하고 있는 동행인지 조회
    int countCompanionByMemberIdAndCompanionId(@Param("memberId") String memberId,
                                               @Param("companionId") int companionId);

    // 동행 생성
    int insertCompanion(CompanionDTO companionDTO);
}
