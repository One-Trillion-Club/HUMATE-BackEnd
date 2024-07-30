package com.otclub.humate.domain.review.mapper;

import com.otclub.humate.common.entity.Review;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    int insertReview(Review review);
}
