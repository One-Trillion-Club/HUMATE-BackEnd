package com.otclub.humate.domain.review.controller;

import com.otclub.humate.common.annotation.MemberId;
import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.review.dto.ReviewRequestDTO;
import com.otclub.humate.domain.review.dto.ReviewResponseDTO;
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
    public ResponseEntity<ReviewResponseDTO> reviewAddPage(@RequestParam("companionId") int companionId,
                                                           @MemberId String memberId) {
        return ResponseEntity.ok(reviewService.findCompanionInfo(companionId, memberId));
    }

    /**
     * 후기 등록
     * @author : 손승완
     */
    @PostMapping
    public ResponseEntity<CommonResponseDTO> reviewAdd(@RequestBody ReviewRequestDTO reviewRequestDTO,
                                                       @MemberId String memberId) {
        reviewService.saveReview(reviewRequestDTO, memberId);
        return ResponseEntity.ok(new CommonResponseDTO(true, "리뷰 등록에 성공했습니다."));
    }


}
