package com.otclub.humate.domain.companion.dto;

import com.otclub.humate.domain.companion.vo.CompanionStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 동행 응답 dto
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
public class CompanionResponseDTO {
    // 동행 ID
    private int companionId;
    // 매칭글 제목
    private String postTitle;
    // 메이트 프로필 이미지 url
    private String mateProfileImgUrl;
    // 메이트 닉네임
    private String mateNickname;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 동행 상태
    private String status;

    public static List<CompanionResponseDTO> ofList(
            List<CompanionDetailsDTO> companionDetailsList,
            String memberId) {

        List<CompanionResponseDTO> responses = new ArrayList<>();
        for (CompanionDetailsDTO companionDetailsDTO : companionDetailsList) {
            CompanionResponseDTOBuilder responseBuilder = CompanionResponseDTO.builder()
                    .companionId(companionDetailsDTO.getCompanionId())
                    .matchDate(companionDetailsDTO.getMatchDate())
                    .matchBranch(companionDetailsDTO.getMatchBranch())
                    .postTitle(companionDetailsDTO.getPostTitle())
                    .status(
                            companionDetailsDTO.getFinishedAt() != null ?
                                    CompanionStatus.COMPLETED.getStatus() :
                                    CompanionStatus.IN_PROGRESS.getStatus());

            if (memberId.equals(companionDetailsDTO.getFirstMemberId())) {
                responseBuilder
                        .mateNickname(companionDetailsDTO.getSecondMemberNickname())
                        .mateProfileImgUrl(companionDetailsDTO.getSecondMemberProfileImgUrl());

            } else {
                responseBuilder
                        .mateNickname(companionDetailsDTO.getFirstMemberNickname())
                        .mateProfileImgUrl(companionDetailsDTO.getFirstMemberProfileImgUrl());
            }

            responses.add(responseBuilder.build());
        }

        return responses;
    }
}
