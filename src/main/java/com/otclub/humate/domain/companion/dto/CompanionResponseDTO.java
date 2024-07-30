package com.otclub.humate.domain.companion.dto;

import com.otclub.humate.domain.companion.vo.CompanionStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CompanionResponseDTO {
    private int companionId;
    private String postTitle;  // 글 제목
    private String mateProfileImgUrl;  // 상대방 프로필 이미지
    private String mateNickname;  // 상대방 이름
    private String matchDate;  // 동행 날짜
    private String status;  // 동행 상태

    public static List<CompanionResponseDTO> ofList(
            List<CompanionDetailsDTO> companionDetailsList,
            String memberId) {

        List<CompanionResponseDTO> responses = new ArrayList<>();
        for (CompanionDetailsDTO companionDetailsDTO : companionDetailsList) {
            CompanionResponseDTOBuilder responseBuilder = CompanionResponseDTO.builder()
                    .companionId(companionDetailsDTO.getCompanionId())
                    .matchDate(companionDetailsDTO.getMatchDate())
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
