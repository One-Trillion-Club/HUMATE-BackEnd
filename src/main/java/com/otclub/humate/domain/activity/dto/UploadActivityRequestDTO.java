package com.otclub.humate.domain.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 활동 업로드 request dto
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
@AllArgsConstructor
@NoArgsConstructor
public class UploadActivityRequestDTO {
    private int activityId;
    private int companionId;
}
