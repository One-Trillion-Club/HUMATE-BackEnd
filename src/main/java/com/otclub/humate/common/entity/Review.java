package com.otclub.humate.common.entity;

import com.otclub.humate.domain.review.dto.ReviewRequestDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

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
