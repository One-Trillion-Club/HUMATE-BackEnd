package com.otclub.humate.domain.activity.mapper;

import com.otclub.humate.common.entity.ActivityEntity;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryResponseDTO;
import com.otclub.humate.domain.activity.dto.NewActivityResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<CompanionActivityHistoryResponseDTO> selectCompanionActivityHistoryList(int companionId);

    List<ActivityEntity> selectActivityList();

    NewActivityResponseDTO selectActivityById(int activityId);

    CompanionActivityHistoryDetailsResponseDTO selectCompanionActivityHistoryById(int companionActivityId);

    List<String> selectImgUrlListById(int companionActivityId);
}