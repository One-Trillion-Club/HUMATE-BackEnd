package com.otclub.humate.domain.activity.service;

import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
import com.otclub.humate.domain.activity.dto.MissionResponseDTO;
import com.otclub.humate.domain.activity.dto.NewMissionDTO;
import com.otclub.humate.domain.activity.dto.UploadActivityRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 활동 service 인터페이스
 * @author 손승완
 * @since 2024.07.30
 * @version 1.2
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * 2024.08.01   손승완        업로드 메서드 추가
 * 2024.08.04   손승완        검증 로직 추가
 * </pre>
 */
public interface ActivityService {

    /**
     * 활동 목록 조회
     *
     * @param companionId
     * @param memberId
     * @return
     */
    MissionResponseDTO findActivities(int companionId, String memberId);

    /**
     * 새로운 활동 조회
     *
     * @param activityId
     * @return
     */
    NewMissionDTO findActivity(int activityId);

    /**
     * 완료된 활동 목록 조회
     *
     * @param companionActivityId
     * @param memberId
     * @return
     */
    CompanionActivityHistoryDetailsResponseDTO findCompanionActivityHistory(int companionActivityId, String memberId);

    /**
     * 수행한 활동 기록
     *
     * @param uploadActivityRequestDTO
     * @param images
     * @param memberId
     */
    void saveCompanionActivityHistory(UploadActivityRequestDTO uploadActivityRequestDTO,
                                      List<MultipartFile> images,
                                      String memberId);


}
