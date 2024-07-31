package com.otclub.humate.domain.mate.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostSearchFilterRequestDTO {

    // 태그 이름 목록 (형식 - "기타, 악세사리")
    private String tagName;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 매칭 성별 (필터링 시 설정한 원하는 성별 - 남, 여)
    private String matchGender;
    // 회원 성별 (검색하는 사람의 성별)
    private String gender;
    // 매칭 언어
    private String matchLanguage;
    // 검색 키워드
    private String keyword;

}
