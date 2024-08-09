package com.otclub.humate.domain.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * 매칭글 전체 정보 등록 Request DTO
 * @author 김지현
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	김지현        최초 생성
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRegisterRequestDTO {

    // 매칭글 ID
    private int postId;
    // 회원 ID
    private String memberId;
    // 매칭글 제목
    private String title;
    // 매칭글 내용
    private String content;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 매칭 성별
    private int matchGender;
    // 매칭 언어
    private String matchLanguage;
    // 매장 및 팝업스토어
    private List<PostPlaceRegisterRequestDTO> postPlaces;
    // 매칭글 태그
    private List<PostTagRegisterRequestDTO> postTags;

}
