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
    private int companionActivityId;
    private String titleKo;
    private String titleEn;
    private int status;
    private String imgUrl;

}
