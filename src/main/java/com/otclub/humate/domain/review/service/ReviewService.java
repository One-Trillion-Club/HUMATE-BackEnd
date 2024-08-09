package com.otclub.humate.domain.review.service;

import com.otclub.humate.domain.review.dto.ReviewRequestDTO;
import com.otclub.humate.domain.review.dto.ReviewResponseDTO;

/**
 * 후기 service 인터페이스
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
public interface ReviewService {
    ReviewResponseDTO findCompanionInfo(int companionId, String memberId);

    void saveReview(ReviewRequestDTO reviewRequestDTO, String memberId);

}
