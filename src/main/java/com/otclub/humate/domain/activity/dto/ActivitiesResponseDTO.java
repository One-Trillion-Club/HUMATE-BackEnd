package com.otclub.humate.domain.activity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ActivitiesResponseDTO {
    private List<CompanionActivityHistoryResponseDTO> companionActivityHistories;
    private List<NewActivityResponseDTO> newActivities;
}
