package com.otclub.humate.domain.activity.service;

import com.otclub.humate.domain.activity.dto.MissionResponseDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.NewMissionDTO;
import com.otclub.humate.domain.activity.dto.UploadActivityRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 활동 서비스 인터페이스
 * @author 손승완
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	손승완        최초 생성
 * </pre>
 */
public interface ActivityService {
    MissionResponseDTO findActivities(int companionId, String memberId);

    NewMissionDTO findActivity(int activityId);

    CompanionActivityHistoryDetailsResponseDTO findCompanionActivityHistory(int companionActivityId, String memberId);

    void saveCompanionActivityHistory(UploadActivityRequestDTO uploadActivityRequestDTO,
                                      List<MultipartFile> images,
                                      String memberId);


}
