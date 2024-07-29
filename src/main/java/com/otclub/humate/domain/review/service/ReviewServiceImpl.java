package com.otclub.humate.domain.review.service;

import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.companion.dto.CompanionPostDTO;
import com.otclub.humate.domain.companion.mapper.CompanionMapper;
import com.otclub.humate.domain.review.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final CompanionMapper companionMapper;

    @Override
    public ReviewResponseDTO findCompanionInfo(int companionId, String memberId) {
        CompanionPostDTO companionPostDTO = companionMapper.
                selectCompanionAndPost(companionId).
                orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_COMPANION));

        return ReviewResponseDTO.of(companionPostDTO, memberId);
    }
}
