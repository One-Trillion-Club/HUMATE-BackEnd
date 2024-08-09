package com.otclub.humate.domain.review.dto;

import com.otclub.humate.domain.companion.dto.CompanionPostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 후기 response dto
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {
    // 동행 ID
    private int companionId;
    // 매칭글 제목
    private String postTitle;
    // 매칭 지점
    private String matchBranch;
    // 매칭 날짜
    private String matchDate;
    // 메이트 닉네임
    private String mateNickname;

    public static ReviewResponseDTO of(CompanionPostDTO companionPostDTO, String memberId) {

        ReviewResponseDTOBuilder responseDTOBuilder = ReviewResponseDTO
                .builder()
                .companionId(companionPostDTO.getCompanionId())
                .postTitle(companionPostDTO.getPostTitle())
                .matchBranch(companionPostDTO.getMatchBranch())
                .matchDate(companionPostDTO.getMatchDate());

        if (companionPostDTO.getFirstMemberId().equals(memberId)) {
            responseDTOBuilder.mateNickname(companionPostDTO.getSecondMemberNickname());
        } else {
            responseDTOBuilder.mateNickname(companionPostDTO.getFirstMemberNickname());
        }

        return responseDTOBuilder.build();
    }
}
