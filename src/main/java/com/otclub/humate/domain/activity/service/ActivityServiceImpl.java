package com.otclub.humate.domain.activity.service;

import com.otclub.humate.common.entity.Activity;
import com.otclub.humate.domain.activity.dto.ActivitiesResponseDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryResponseDTO;
import com.otclub.humate.domain.activity.dto.NewActivityResponseDTO;
import com.otclub.humate.domain.activity.mapper.ActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityMapper activityMapper;
    @Override
    public ActivitiesResponseDTO findActivities(int companionId) {
        // 완료된 활동 목록과 새로운 활동 목록 2개를 조회해야한다.
        List<CompanionActivityHistoryResponseDTO> companionActivityHistories = activityMapper.selectCompanionActivityHistoryList(companionId);
        List<Activity> activities = activityMapper.selectActivityList();

        for (CompanionActivityHistoryResponseDTO companionActivityHistory : companionActivityHistories) {
            for (Activity activity : activities) {
                if (companionActivityHistory.getTitle().equals(activity.getTitle())) {
                    companionActivityHistory.setTitle(activity.getTitle());
                    activities.remove(activity);
                    break;
                }
            }
        }

        return ActivitiesResponseDTO.of(companionActivityHistories, activities);
    }

    @Override
    public NewActivityResponseDTO findActivity(int activityId) {
        return activityMapper.selectActivityById(activityId);
    }

    @Override
    public CompanionActivityHistoryDetailsResponseDTO findCompanionActivityHistory(int companionActivityId) {
        return activityMapper.selectCompanionActivityHistoryById(companionActivityId);
    }
}
