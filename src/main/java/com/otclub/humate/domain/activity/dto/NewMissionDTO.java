package com.otclub.humate.domain.activity.dto;

import com.otclub.humate.common.entity.Activity;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 새로운 활동 상세 페이지에 보여지는 dto
 * @author 손승완
 * @since 2024.07.30
 * @version 1.1
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * 2024.08.06   손승완        title, content 언어별로 추가
 * </pre>
 */
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
