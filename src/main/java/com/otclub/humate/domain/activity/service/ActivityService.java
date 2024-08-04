package com.otclub.humate.domain.activity.service;

import com.otclub.humate.domain.activity.dto.MissionResponseDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.NewMissionDTO;
import com.otclub.humate.domain.activity.dto.UploadActivityRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ActivityService {
    MissionResponseDTO findActivities(int companionId, String memberId);
    NewMissionDTO findActivity(int activityId);
    CompanionActivityHistoryDetailsResponseDTO findCompanionActivityHistory(int companionActivityId, String memberId);
    void saveCompanionActivityHistory(UploadActivityRequestDTO uploadActivityRequestDTO,
                                      List<MultipartFile> images,
                                      String memberId);


}
