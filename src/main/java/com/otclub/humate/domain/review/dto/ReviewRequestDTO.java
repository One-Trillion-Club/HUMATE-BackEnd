package com.otclub.humate.domain.review.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewRequestDTO {
    private int companionId;
    private String content;
    private double score;
}
