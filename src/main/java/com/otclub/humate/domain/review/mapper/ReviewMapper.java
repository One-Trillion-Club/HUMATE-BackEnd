package com.otclub.humate.domain.review.mapper;

import com.otclub.humate.common.entity.Review;
import org.apache.ibatis.annotations.Mapper;

/**
 * 후기 mapper
 * @author 손승완
 * @since 2024.07.26
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26  	손승완        최초 생성
 * </pre>
 */
@Mapper
public interface ReviewMapper {
    void insertReviewAndUpdateManner(Review review);
}
