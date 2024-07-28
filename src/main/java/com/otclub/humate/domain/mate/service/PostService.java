package com.otclub.humate.domain.mate.service;

import com.otclub.humate.domain.mate.dto.PostListResponseDTO;

import java.util.List;

public interface PostService {

    // 게시글 전체 목록 조회
    List<PostListResponseDTO> getAllPosts();
}
