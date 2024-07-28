package com.otclub.humate.domain.activity.controller;

import com.otclub.humate.domain.activity.dto.ActivitiesResponseDTO;
import com.otclub.humate.domain.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{companionId}")
    public ResponseEntity<ActivitiesResponseDTO> activityList(@PathVariable("companionId") int companionId) {
        // 1. 동행 ID와 회원 정보를 토대로 활동 목록을 불러온다.
        int memberId = 1; // 임시 데이터
        // 2. 이때, 완료된 활동 목록과 새로운 활동 목록 두 가지를 함께 불러온다.
        return ResponseEntity.ok(activityService.findActivities(companionId));
    }
}
