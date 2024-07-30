package com.otclub.humate.domain.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadActivityRequestDTO {
    private int activityId;
    private int companionId;
}
