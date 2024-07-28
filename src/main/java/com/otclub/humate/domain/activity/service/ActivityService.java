package com.otclub.humate.domain.activity.service;

import com.otclub.humate.domain.activity.dto.ActivitiesResponseDTO;

public interface ActivityService {
    ActivitiesResponseDTO findActivities(int companionId);
}
