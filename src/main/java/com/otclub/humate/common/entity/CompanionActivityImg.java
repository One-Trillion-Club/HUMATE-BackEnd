package com.otclub.humate.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 활동 인증 내역 img Entity
 * @author 손승완
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29   손승완        최초 생성
 * </pre>
 */
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
