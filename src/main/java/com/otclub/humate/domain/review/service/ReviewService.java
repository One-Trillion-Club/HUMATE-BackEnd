package com.otclub.humate.domain.review.service;

import com.otclub.humate.domain.review.dto.ReviewResponseDTO;

public interface ReviewService {
    ReviewResponseDTO findCompanionInfo(int companionId, String memberId);

}
