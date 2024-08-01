package com.otclub.humate.domain.activity.controller;

import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.activity.dto.MissionResponseDTO;
import com.otclub.humate.domain.activity.dto.NewMissionDTO;
import com.otclub.humate.domain.activity.dto.UploadActivityRequestDTO;
import com.otclub.humate.domain.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/activities")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ActivityController {
    private final ActivityService activityService;

    /**
     * 특정 동행의 활동(완료된, 새로운) 목록 조회
     *
     * @param : 동행 ID
     * @author : 손승완
     */
    @GetMapping
    public ResponseEntity<MissionResponseDTO> activityList(@RequestParam("companionId") int companionId) {
        return ResponseEntity.ok(activityService.findActivities(companionId));
    }

    /**
     * 새로운 활동 상세 조회
     *
     * @param : 활동 인증 내역 ID
     * @author : 손승완
     */
    @GetMapping("/{activityId}")
    public ResponseEntity<NewMissionDTO> NewActivityDetails(@PathVariable("activityId") int activityId) {
        return ResponseEntity.ok(activityService.findActivity(activityId));
    }

    /**
     * 완료된 활동 상세 조회
     *
     * @param : 활동 인증 내역 ID
     * @author : 손승완
     */
    @GetMapping("/histories/{companionActivityId}")
    public ResponseEntity<Object> finishedActivityDetails(@PathVariable("companionActivityId") int companionActivityId) {
        return ResponseEntity.ok(activityService.findCompanionActivityHistory(companionActivityId));
    }

    /**
     * 활동 기록하기
     *
     * @param : 활동 ID, 동행 ID, 이미지 파일 목록
     * @author : 손승완
     */
    @PostMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CommonResponseDTO> activityUpload(
                                                            @RequestPart("uploadActivityRequestDTO") UploadActivityRequestDTO uploadActivityRequestDTO,
                                                            @RequestPart("images") List<MultipartFile> images) {

        activityService.saveCompanionActivityHistory(uploadActivityRequestDTO, images);
        return ResponseEntity.ok(new CommonResponseDTO(true, "활동 기록이 완료되었습니다."));
    }
}
