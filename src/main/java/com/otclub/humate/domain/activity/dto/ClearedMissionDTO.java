package com.otclub.humate.domain.activity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ClearedMissionDTO {
    private int companionActivityId;
    private String title;
    private int status;
    private String imgUrl;

}
