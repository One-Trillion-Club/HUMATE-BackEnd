package com.otclub.humate.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CompanionActivityImg {
    private int companionActivityImgId;
    private int companionActivityId;
    private String imgUrl;
    private Date createdAt;

    public static CompanionActivityImg of(String imgUrl) {
        return CompanionActivityImg.builder()
                .imgUrl(imgUrl)
                .build();
    }
}
