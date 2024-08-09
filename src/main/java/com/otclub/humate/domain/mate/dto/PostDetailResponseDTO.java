package com.otclub.humate.domain.mate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 매칭글 상세 정보 Response DTO
 * @author 김지현
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	김지현        최초 생성
 * 2024.08.03   김지현        이미지 프로필 필드 추가
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailResponseDTO {

    // 매칭글 ID
    private int postId;
    // 회원 ID
    private String memberId;
    // 회원 프로필 이미지
    private String profileImgUrl;
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
    // 매칭 여부
    private int isMatched;
    // 매장 및 팝업스토어
    private List<PostPlaceDetailResponseDTO> postPlaces;
    // 매칭글 태그
    private List<PostTagDetailResponseDTO> postTags;

}
