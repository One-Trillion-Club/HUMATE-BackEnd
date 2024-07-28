package com.otclub.humate.domain.mate.mapper;

import com.otclub.humate.domain.mate.dto.PostListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    // 게시글 전체 목록 조회
    List<PostListResponseDTO> selectAllPosts();
}
