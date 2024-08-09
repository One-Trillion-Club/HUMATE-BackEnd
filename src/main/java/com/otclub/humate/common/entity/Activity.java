package com.otclub.humate.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 활동 Entity
 * @author 손승완
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27   손승완        최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    // 활동 ID
    private int activityId;

    // 한국어 제목
    private String titleKo;

    // 영어 제목
    private String titleEn;

    // 한국어 내용
    private String contentKo;

    // 영어 내용
    private String contentEn;

    // 포인트
    private int point;

    // 이미지 URL
    private String imgUrl;

    // 생성 날짜
    private Date createdAt;

    // 삭제 날짜
    private Date deletedAt;
}
