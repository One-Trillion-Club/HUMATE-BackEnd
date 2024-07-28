package com.otclub.humate.domain.activity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanionActivityHistoryDetailsResponseDTO {
    private String activityTitle;
    private Date createdAt;
    private List<String> imgUrls;
}
