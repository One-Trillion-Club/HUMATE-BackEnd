package com.otclub.humate.domain.activity.dto;

import com.otclub.humate.common.entity.Activity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MissionResponseDTO {
    private String postTitle;
    private List<ClearedMissionDTO> clearedMissionList;
    private List<NewMissionDTO> newMissionList;

    public static MissionResponseDTO of(List<ClearedMissionDTO> companionActivityHistories,
                                        List<Activity> activities,
                                        String postTitle) {
        return MissionResponseDTO.builder()
                .clearedMissionList(companionActivityHistories)
                .newMissionList(NewMissionDTO.ofList(activities))
                .postTitle(postTitle)
                .build();
    }
}
