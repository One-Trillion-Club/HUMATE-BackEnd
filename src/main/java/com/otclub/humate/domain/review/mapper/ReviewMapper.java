package com.otclub.humate.domain.review.mapper;

import com.otclub.humate.common.entity.Review;
import org.apache.ibatis.annotations.Mapper;

/**
 * 후기 mapper
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
@Mapper
public interface ReviewMapper {
    void insertReviewAndUpdateManner(Review review);
}
