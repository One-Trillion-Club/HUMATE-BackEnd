package com.otclub.humate.domain.review.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewRequestDTO {
    private int companionId;
    private String content;
    private int score;
}
