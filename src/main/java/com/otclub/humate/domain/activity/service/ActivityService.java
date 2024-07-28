package com.otclub.humate.domain.activity.service;

import com.otclub.humate.domain.activity.dto.ActivitiesResponseDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.NewActivityResponseDTO;

public interface ActivityService {
    ActivitiesResponseDTO findActivities(int companionId);
    NewActivityResponseDTO findActivity(int activityId);
    CompanionActivityHistoryDetailsResponseDTO findCompanionActivityHistory(int companionActivityId);

}
