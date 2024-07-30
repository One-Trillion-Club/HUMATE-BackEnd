package com.otclub.humate.domain.review.controller;

import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.review.dto.ReviewRequestDTO;
import com.otclub.humate.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 후기 남기기 페이지 조회
     * @author : 손승완
     * @param : 동행 ID
     *
     */
    @GetMapping
    public ResponseEntity<Object> reviewAddPage(@RequestParam("companionId") int companionId) {
        String memberId = "K_1"; // sample data
        return ResponseEntity.ok(reviewService.findCompanionInfo(companionId, memberId));
    }

    /**
     * 후기 등록
     * @author : 손승완
     */
    @PostMapping
    public ResponseEntity<CommonResponseDTO> reviewAdd(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        String memberId = "K_1"; // test data
        reviewService.saveReview(reviewRequestDTO, memberId);
        return ResponseEntity.ok(new CommonResponseDTO(true, "리뷰 등록에 성공했습니다."));
    }


}
