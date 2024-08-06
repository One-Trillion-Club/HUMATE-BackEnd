package com.otclub.humate.domain.activity.dto;

import com.otclub.humate.common.entity.Activity;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class NewMissionDTO {
    private int activityId;
    private String titleKo;
    private String titleEn;
    private String contentKo;
    private String contentEn;
    private int point;
    private String imgUrl;

    public static List<NewMissionDTO> ofList(List<Activity> activities) {
        List<NewMissionDTO> newActivitiesResponseDTO = new ArrayList<>();
        for (Activity activity : activities) {
            newActivitiesResponseDTO.add(NewMissionDTO.builder()
                    .activityId(activity.getActivityId())
                    .titleKo(activity.getTitleKo())
                    .titleEn(activity.getTitleEn())
                    .imgUrl(activity.getImgUrl())
                    .build()
            );
        }

        return newActivitiesResponseDTO;
    }
}
