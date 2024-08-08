package com.otclub.humate.domain.activity.controller;

import com.otclub.humate.common.annotation.MemberId;
import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.activity.dto.CompanionActivityHistoryDetailsResponseDTO;
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

/**
 * 활동 컨트롤러
 * @author 손승완
 * @since 2024.07.26
 * @version 1.2
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	손승완        최초 생성
 * 2024.07.29   손승완        활동 업로드 기능 추가
 * 2024.08.04   손승완        쿠키에 담긴 회원 아이디 불러오도록 수정
 * </pre>
 */
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
    public ResponseEntity<MissionResponseDTO> activityList(@RequestParam("companionId") int companionId,
                                                           @MemberId String memberId) {
        return ResponseEntity.ok(activityService.findActivities(companionId, memberId));
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
    public ResponseEntity<CompanionActivityHistoryDetailsResponseDTO> finishedActivityDetails(@PathVariable("companionActivityId") int companionActivityId, @MemberId String memberId) {
        return ResponseEntity.ok(activityService.findCompanionActivityHistory(companionActivityId, memberId));
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
                                                            @RequestPart("images") List<MultipartFile> images,
                                                            @MemberId String memberId) {

        activityService.saveCompanionActivityHistory(uploadActivityRequestDTO, images, memberId);
        return ResponseEntity.ok(new CommonResponseDTO(true, "활동 기록이 완료되었습니다."));
    }
}
