package com.otclub.humate.domain.activity.controller;

import com.otclub.humate.domain.activity.dto.ActivitiesResponseDTO;
import com.otclub.humate.domain.activity.dto.NewActivityResponseDTO;
import com.otclub.humate.domain.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/activities")
@RestController
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

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

}
