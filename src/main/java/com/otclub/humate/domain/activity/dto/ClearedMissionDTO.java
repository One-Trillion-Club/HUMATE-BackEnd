package com.otclub.humate.domain.activity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 완료된 활동 목록에 보여지는 dto
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
public class ClearedMissionDTO {
    // 활동 수행 내역 ID
    private int companionActivityId;
    // 활동 환국어 제목
    private String titleKo;
    // 활동 영어 제목
    private String titleEn;
    // 활동 상태
    private int status;
    // 활동 썸네일 이미지 url
    private String imgUrl;

}
