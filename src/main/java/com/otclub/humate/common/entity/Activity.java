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
