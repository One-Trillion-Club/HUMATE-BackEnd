package com.otclub.humate.domain.activity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 완료된 활동 상세 페이지에 보여지는 dto
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
@AllArgsConstructor
@NoArgsConstructor
public class CompanionActivityHistoryDetailsResponseDTO {
    // 활동 한국어 제목
    private String activityTitleKo;
    // 활동 영어 제목
    private String activityTitleEn;
    // 활동 생성일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private Date createdAt;
    // 활동 이미지 url 목록
    private List<String> imgUrls;
}
