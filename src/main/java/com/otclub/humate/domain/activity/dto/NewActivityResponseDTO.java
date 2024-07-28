package com.otclub.humate.domain.activity.dto;

import com.otclub.humate.common.entity.ActivityEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class NewActivityResponseDTO {
    private int activityId;
    private String title;
    private String imgUrl;

    public static List<NewActivityResponseDTO> ofList(List<ActivityEntity> activities) {
        List<NewActivityResponseDTO> newActivitiesResponseDTO = new ArrayList<>();
        for (ActivityEntity activity : activities) {
            newActivitiesResponseDTO.add(NewActivityResponseDTO.builder()
                    .activityId(activity.getActivityId())
                    .title(activity.getTitle())
                    .imgUrl(activity.getImgUrl())
                    .build()
            );
        }

        return newActivitiesResponseDTO;
    }
}
