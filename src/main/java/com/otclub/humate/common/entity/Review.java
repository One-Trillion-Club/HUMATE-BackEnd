package com.otclub.humate.common.entity;

import com.otclub.humate.domain.review.dto.ReviewRequestDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

/**
 * 활동 Entity
 * @author 손승완
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30   손승완        최초 생성
 * 2024.08.05   손승완        score double 처리
 * </pre>
 */
@Getter
@Builder
public class Review {
    private int reviewId;
    private int companionId;
    private String reviewerId;
    private String revieweeId;
    private String content;
    private double score;
    private Date createdAt;
    private String status;

    public static Review of(ReviewRequestDTO reviewRequestDTO, Companion companion, String memberId) {
        ReviewBuilder reviewBuilder = Review.builder()
                .companionId(companion.getCompanionId())
                .content(reviewRequestDTO.getContent())
                .score(reviewRequestDTO.getScore());

        if (memberId.equals(companion.getFirstMemberId())) {
            reviewBuilder
                    .reviewerId(companion.getFirstMemberId())
                    .revieweeId(companion.getSecondMemberId());
        } else {
            reviewBuilder
                    .reviewerId(companion.getSecondMemberId())
                    .revieweeId(companion.getFirstMemberId());
        }

        return reviewBuilder.build();
    }
}
