package com.otclub.humate.domain.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 동행에 대한 정보를 담는 dto
 * @author 손승완
 * @since 2024.08.04
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.04  	손승완        최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanionInfoDTO {
    // 매칭 제목
    private String postTitle;
    // 매칭 종료 상태
    private int isFinished;

}
