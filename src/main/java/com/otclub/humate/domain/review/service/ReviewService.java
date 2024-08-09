package com.otclub.humate.domain.review.service;

import com.otclub.humate.domain.review.dto.ReviewRequestDTO;
import com.otclub.humate.domain.review.dto.ReviewResponseDTO;

/**
 * 후기 service 인터페이스
 * @author 손승완
 * @since 2024.07.30
 * @version 1.1
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * 2024.08.05   손승완        후기 등록 쿼리 프로시저로 변환
 * </pre>
 */
public interface ReviewService {

    /**
     * 동행 정보 조회
     *
     * @param companionId
     * @param memberId
     * @return
     */
    ReviewResponseDTO findCompanionInfo(int companionId, String memberId);

    /**
     * 후기 등록
     *
     * @param reviewRequestDTO
     * @param memberId
     */
    void saveReview(ReviewRequestDTO reviewRequestDTO, String memberId);

}
