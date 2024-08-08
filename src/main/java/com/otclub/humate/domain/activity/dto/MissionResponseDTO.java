package com.otclub.humate.domain.activity.dto;

import com.otclub.humate.common.entity.Activity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 활동 목록 전체 데이터 dto
 * @author 손승완
 * @since 2024.08.01
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.01  	손승완        최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
public class MissionResponseDTO {
    private int isFinished;
    private String postTitle;
    private List<ClearedMissionDTO> clearedMissionList;
    private List<NewMissionDTO> newMissionList;

    public static MissionResponseDTO of(List<ClearedMissionDTO> companionActivityHistories,
                                        List<Activity> activities,
                                        CompanionInfoDTO companionInfoDTO) {
        return MissionResponseDTO.builder()
                .clearedMissionList(companionActivityHistories)
                .newMissionList(NewMissionDTO.ofList(activities))
                .postTitle(companionInfoDTO.getPostTitle())
                .isFinished(companionInfoDTO.getIsFinished())
                .build();
    }
}
