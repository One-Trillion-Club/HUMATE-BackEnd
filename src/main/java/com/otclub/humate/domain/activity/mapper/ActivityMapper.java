package com.otclub.humate.domain.activity.mapper;

import com.otclub.humate.common.entity.Activity;
import com.otclub.humate.common.entity.CompanionActivityHistory;
import com.otclub.humate.common.entity.CompanionActivityImg;
import com.otclub.humate.domain.activity.dto.ClearedMissionDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.NewMissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 활동 매퍼 인터페이스
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * 2024.08.01   손승완        활동 목록 조회 쿼리 추가
 * 2024.08.03   손승완        이미지 url list 저장 쿼리 추가
 * 2024.08.06   손승완        동행 검증 쿼리 추가
 * </pre>
 */
@Mapper
public interface ActivityMapper {
    // 완료된 활동 목록 조회
    List<ClearedMissionDTO> selectCompanionActivityHistoryList(int companionId);

    // 새로운 활동 목록 조회
    List<Activity> selectActivityList();

    // 새로운 활동 상세 조회
    NewMissionDTO selectActivityById(int activityId);

    // 완료된 활동 상세 조회
    Optional<CompanionActivityHistoryDetailsResponseDTO> selectCompanionActivityHistoryById(@Param("companionActivityId") int companionActivityId,
                                                                                            @Param("memberId") String memberId);

    // 완료된 활동 이미지 url 목록 조회
    List<String> selectImgUrlListById(int companionActivityId);

    // 동행 검증
    int validateInsertCompanionActivity(@Param("companionId") int companionId,
                                        @Param("activityId") int activityId);
    // 수행한 활동 저장
    int insertCompanionActivityHistory(CompanionActivityHistory companionActivityHistory);

    // 수행한 활동 이미지 목록 저장
    int insertCompanionActivityImgList(List<CompanionActivityImg> imgs);


}