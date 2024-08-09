package com.otclub.humate.domain.mate.dto;

import lombok.*;

import java.util.List;

/**
 * 매칭글 검색 필터 정보 Request DTO
 * @author 김지현
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.31  	김지현        최초 생성
 * 2024.08.02   김지현        태그 개수 필드 추가
 * </pre>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSearchFilterRequestDTO {

    // 태그 이름 (형식 - "기타, 악세사리")
    private String tagName;
    // 태그 이름 리스트 (형식 - ["기타", "악세서리"]
    private List<String> tags;
    // 태그 개수
    private int tagCount;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 매칭 성별 (필터링 시 설정한 원하는 성별 - 남, 여)
    private String matchGender;
    // 회원 ID
    private String memberId;
    // 회원 성별 (검색하는 사람의 성별)
    private String gender;
    // 매칭 언어
    private String matchLanguage;
    // 검색 키워드
    private String keyword;

}
