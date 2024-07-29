package com.otclub.humate.domain.activity.controller;

import com.otclub.humate.common.upload.S3Uploader;
import com.otclub.humate.domain.activity.dto.ActivitiesResponseDTO;
import com.otclub.humate.domain.activity.dto.NewActivityResponseDTO;
import com.otclub.humate.domain.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/activities")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ActivityController {
    private final ActivityService activityService;
    private final S3Uploader s3Uploader;

    /**
     * 특정 동행의 활동(완료된, 새로운) 목록 조회
     *
     * @author : 손승완
     * @param : 동행 ID
     *
     */
    @GetMapping
    public ResponseEntity<ActivitiesResponseDTO> activityList(@RequestParam("companionId") int companionId) {
        return ResponseEntity.ok(activityService.findActivities(companionId));
    }

    /**
     * 새로운 활동 상세 조회
     * @author : 손승완
     * @param : 활동 인증 내역 ID
     */
    @GetMapping("/{activityId}")
    public ResponseEntity<NewActivityResponseDTO> NewActivityDetails(@PathVariable("activityId") int activityId) {
        return ResponseEntity.ok(activityService.findActivity(activityId));
    }

    /**
     * 완료된 활동 상세 조회
     * @author : 손승완
     * @param : 활동 인증 내역 ID
     */
    @GetMapping("/histories/{companionActivityId}")
    public ResponseEntity<Object> finishedActivityDetails(@PathVariable("companionActivityId") int companionActivityId) {
        return ResponseEntity.ok(activityService.findCompanionActivityHistory(companionActivityId));
    }

    /**
     * 활동 기록하기
     * @author : 손승완
     * @param : 활동 ID
     * @param : 동행 ID
     */
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadActivity(@RequestParam("activityId") int activityId,
                                                 @RequestParam("companionId") int companionId,
                                                 @RequestPart("image") MultipartFile imageFile) {

        // 해당 동행에 대해서 이미 올린 활동인지 먼저 검증해야한다.
        // 이미 올렸다면 예외 처리

        log.info(s3Uploader.uploadImage(imageFile));

        return ResponseEntity.ok("good");
    }


}
