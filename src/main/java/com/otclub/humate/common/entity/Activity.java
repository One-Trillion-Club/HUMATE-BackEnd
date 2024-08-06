package com.otclub.humate.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private int activityId;
    private String titleKo;
    private String titleEn;
    private String contentKo;
    private String contentEn;
    private int point;
    private String imgUrl;
    private Date createdAt;
    private Date deletedAt;
}
