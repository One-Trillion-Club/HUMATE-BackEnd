package com.otclub.humate.domain.activity.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ActivityVO {
    private Long activityId;
    private String title;
    private String content;
    private int point;
    private String imgUrl;
    private Date createdAt;
    private Date deletedAt;
}
