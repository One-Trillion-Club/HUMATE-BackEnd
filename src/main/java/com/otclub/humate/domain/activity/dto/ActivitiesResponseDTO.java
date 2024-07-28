package com.otclub.humate.domain.activity.dto;

import com.otclub.humate.common.entity.Activity;
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

    public static ActivitiesResponseDTO of(List<CompanionActivityHistoryResponseDTO> companionActivityHistories, List<Activity> activities) {
        return ActivitiesResponseDTO.builder()
                .companionActivityHistories(companionActivityHistories)
                .newActivities(NewActivityResponseDTO.ofList(activities))
                .build();
    }
}
