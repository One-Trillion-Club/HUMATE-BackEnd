package com.otclub.humate.domain.review.service;

import com.otclub.humate.common.entity.Companion;
import com.otclub.humate.common.entity.Review;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.companion.dto.CompanionPostDTO;
import com.otclub.humate.domain.companion.mapper.CompanionMapper;
import com.otclub.humate.domain.review.dto.ReviewRequestDTO;
import com.otclub.humate.domain.review.dto.ReviewResponseDTO;
import com.otclub.humate.domain.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 후기 service 구현체
 * @author 손승완
 * @since 2024.07.30
 * @version 1.1
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * 2024.08.05   손승완        후기 등록 쿼리 프로시저로 변환
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final CompanionMapper companionMapper;
    private final ReviewMapper reviewMapper;

    /**
     * 동행 정보 조회
     *
     * @param companionId
     * @param memberId
     * @return
     */
    @Override
    public ReviewResponseDTO findCompanionInfo(int companionId, String memberId) {
        CompanionPostDTO companionPostDTO = companionMapper
                .selectCompanionAndPost(companionId, memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_COMPANION));

        return ReviewResponseDTO.of(companionPostDTO, memberId);
    }

    /**
     * 후기 등록
     *
     * @param reviewRequestDTO
     * @param memberId
     */
    @Override
    @Transactional
    public void saveReview(ReviewRequestDTO reviewRequestDTO, String memberId) {
        Companion companion = companionMapper
                .selectCompanionByIds(reviewRequestDTO.getCompanionId(), memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_COMPANION));

        Review review = Review.of(reviewRequestDTO, companion, memberId);
        reviewMapper.insertReviewAndUpdateManner(review);
        if (review.getStatus().equals("Failure")) {
            throw new CustomException(ErrorCode.REVIEW_FAIL);
        }
    }
}