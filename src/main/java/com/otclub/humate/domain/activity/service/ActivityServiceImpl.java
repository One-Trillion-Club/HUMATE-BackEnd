package com.otclub.humate.domain.activity.service;

import com.otclub.humate.common.entity.Activity;
import com.otclub.humate.common.entity.CompanionActivityHistory;
import com.otclub.humate.common.entity.CompanionActivityImg;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.common.upload.S3Uploader;
import com.otclub.humate.domain.activity.dto.*;
import com.otclub.humate.domain.activity.mapper.ActivityMapper;
import com.otclub.humate.domain.companion.mapper.CompanionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 활동 service 인터페이스
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * 2024.08.01   손승완        업로드 메서드 추가
 * 2024.08.04   손승완        검증 로직 추가
 * </pre>
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    private final ActivityMapper activityMapper;
    private final CompanionMapper companionMapper;
    private final S3Uploader s3Uploader;

    /**
     * 활동 목록 조회
     *
     * @param companionId
     * @param memberId
     * @exception CustomException NOT_EXISTS_COMPANION
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public MissionResponseDTO findActivities(int companionId, String memberId) {

        // 유효한 회원인지 검증
        if (companionMapper.countCompanionByMemberIdAndCompanionId(memberId, companionId) == 0) {
            throw new CustomException(ErrorCode.NOT_EXISTS_COMPANION);
        }

        // 글 제목 조회
        CompanionInfoDTO companionInfoDTO = companionMapper.selectPostTitleById(companionId);

        // 완료된 활동 목록과 새로운 활동 목록 2개를 조회해야한다.
        List<ClearedMissionDTO> companionActivityHistories = activityMapper.selectCompanionActivityHistoryList(companionId);
        List<Activity> activities = activityMapper.selectActivityList();

        for (ClearedMissionDTO companionActivityHistory : companionActivityHistories) {
            for (Activity activity : activities) {
                if (companionActivityHistory.getTitleKo().equals(activity.getTitleKo())) {
                    companionActivityHistory.setTitleKo((activity.getTitleKo()));
                    companionActivityHistory.setTitleEn((activity.getTitleEn()));
                    activities.remove(activity);
                    break;
                }
            }
        }

        return MissionResponseDTO.of(companionActivityHistories, activities, companionInfoDTO);
    }

    /**
     * 새로운 활동 조회
     *
     * @param activityId
     * @return
     */
    @Override
    public NewMissionDTO findActivity(int activityId) {
        return activityMapper.selectActivityById(activityId);
    }

    /**
     * 완료된 활동 목록 조회
     *
     * @param companionActivityId
     * @param memberId
     * @exception CustomException FORBIDDEN_REQUEST
     * @return
     */
    @Override
    public CompanionActivityHistoryDetailsResponseDTO findCompanionActivityHistory(int companionActivityId, String memberId) {
        return activityMapper.selectCompanionActivityHistoryById(companionActivityId, memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.FORBIDDEN_REQUEST));
    }

    /**
     * 수행한 활동 기록
     *
     * @param uploadActivityRequestDTO
     * @param images
     * @param memberId
     * @exception CustomException ALREADY_EXISTS_ACTIVITY, NOT_EXISTS_COMPANION, UPLOAD_FAIL
     */
    @Override
    @Transactional
    public void saveCompanionActivityHistory(UploadActivityRequestDTO uploadActivityRequestDTO,
                                             List<MultipartFile> images,
                                             String memberId) {

        int companionId = uploadActivityRequestDTO.getCompanionId();
        int activityId = uploadActivityRequestDTO.getActivityId();

        // 동행이 종료되었거나, 이미 등록한 활동인지 검증
        if (activityMapper.validateInsertCompanionActivity(companionId, activityId) != 0) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_ACTIVITY);
        }

        // 유효한 회원인지 검증
        if (companionMapper.countCompanionByMemberIdAndCompanionId(memberId, companionId) == 0) {
            throw new CustomException(ErrorCode.NOT_EXISTS_COMPANION);
        }

        List<CompanionActivityImg> companionActivityImgList = new ArrayList<>();
        for (MultipartFile imageFile : images) {
            companionActivityImgList.add(CompanionActivityImg.of(s3Uploader.uploadImage(imageFile)));
        }

        CompanionActivityHistory history = CompanionActivityHistory.of(companionId, activityId);
        if (activityMapper.insertCompanionActivityHistory(history) != 1) {
            throw new CustomException(ErrorCode.UPLOAD_FAIL);
        }

        for (CompanionActivityImg img : companionActivityImgList) {
            img.setCompanionActivityId(history.getCompanionActivityId());
        }

        // bulk insert
        if (activityMapper.insertCompanionActivityImgList(companionActivityImgList) != companionActivityImgList.size()) {
            throw new CustomException(ErrorCode.UPLOAD_FAIL);
        }
    }
}
