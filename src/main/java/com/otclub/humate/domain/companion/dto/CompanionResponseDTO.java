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
    private int companionId;
    private String postTitle;  // 글 제목
    private String mateProfileImgUrl;  // 상대방 프로필 이미지
    private String mateNickname;  // 상대방 이름
    private String matchDate;  // 동행 날짜
    private String matchBranch;
    private String status;  // 동행 상태

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
