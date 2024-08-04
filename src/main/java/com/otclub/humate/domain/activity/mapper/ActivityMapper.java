package com.otclub.humate.domain.activity.mapper;

import com.otclub.humate.common.entity.Activity;
import com.otclub.humate.common.entity.CompanionActivityHistory;
import com.otclub.humate.common.entity.CompanionActivityImg;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.ClearedMissionDTO;
import com.otclub.humate.domain.activity.dto.NewMissionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ActivityMapper {
    List<ClearedMissionDTO> selectCompanionActivityHistoryList(int companionId);

    List<Activity> selectActivityList();

    NewMissionDTO selectActivityById(int activityId);

    Optional<CompanionActivityHistoryDetailsResponseDTO> selectCompanionActivityHistoryById(@Param("companionActivityId") int companionActivityId,
                                                                                            @Param("memberId") String memberId);

    List<String> selectImgUrlListById(int companionActivityId);

    int countCompanionActivityHistoryByIds(@Param("companionId") int companionId,
                                        @Param("activityId") int activityId);
    int insertCompanionActivityHistory(CompanionActivityHistory companionActivityHistory);

    int insertCompanionActivityImgList(List<CompanionActivityImg> imgs);


}