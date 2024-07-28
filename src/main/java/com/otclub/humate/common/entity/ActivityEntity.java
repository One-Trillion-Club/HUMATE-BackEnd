package com.otclub.humate.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityEntity {
    private int activityId;
    private String title;
    private String content;
    private int point;
    private String imgUrl;
    private Date createdAt;
    private Date deletedAt;
}
