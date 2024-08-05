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

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final CompanionMapper companionMapper;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewResponseDTO findCompanionInfo(int companionId, String memberId) {
        CompanionPostDTO companionPostDTO = companionMapper
                .selectCompanionAndPost(companionId, memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_COMPANION));

        return ReviewResponseDTO.of(companionPostDTO, memberId);
    }

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