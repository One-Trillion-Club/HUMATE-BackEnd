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

/**
 * 후기 controller
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 후기 등록 페이지 조회
     *
     * @param companionId
     * @param memberId
     * @return
     */
    @GetMapping
    public ResponseEntity<ReviewResponseDTO> reviewAddPage(@RequestParam("companionId") int companionId,
                                                           @MemberId String memberId) {
        return ResponseEntity.ok(reviewService.findCompanionInfo(companionId, memberId));
    }

    /**
     * 후기 등록
     *
     * @param reviewRequestDTO
     * @param memberId
     * @return
     */
    @PostMapping
    public ResponseEntity<CommonResponseDTO> reviewAdd(@RequestBody ReviewRequestDTO reviewRequestDTO,
                                                       @MemberId String memberId) {
        reviewService.saveReview(reviewRequestDTO, memberId);
        return ResponseEntity.ok(new CommonResponseDTO(true, "리뷰 등록에 성공했습니다."));
    }


}
