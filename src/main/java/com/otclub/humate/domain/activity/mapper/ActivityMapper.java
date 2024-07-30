package com.otclub.humate.domain.activity.mapper;

import com.otclub.humate.common.entity.Activity;
import com.otclub.humate.common.entity.CompanionActivityHistory;
import com.otclub.humate.common.entity.CompanionActivityImg;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryResponseDTO;
import com.otclub.humate.domain.activity.dto.NewActivityResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<CompanionActivityHistoryResponseDTO> selectCompanionActivityHistoryList(int companionId);

    List<Activity> selectActivityList();

    NewActivityResponseDTO selectActivityById(int activityId);

    CompanionActivityHistoryDetailsResponseDTO selectCompanionActivityHistoryById(int companionActivityId);

    List<String> selectImgUrlListById(int companionActivityId);

    int countCompanionActivityHistoryByIds(@Param("companionId") int companionId,
                                        @Param("activityId") int activityId);
    int insertCompanionActivityHistory(CompanionActivityHistory companionActivityHistory);

    int insertCompanionActivityImgList(List<CompanionActivityImg> imgs);


}