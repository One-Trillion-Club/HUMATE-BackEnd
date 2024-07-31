package com.otclub.humate.domain.mate.service;

import com.otclub.humate.domain.mate.dto.PostDetailResponseDTO;
import com.otclub.humate.domain.mate.dto.PostListResponseDTO;
import com.otclub.humate.domain.mate.dto.PostRegisterRequestDTO;
import com.otclub.humate.domain.mate.dto.PostSearchFilterRequestDTO;

import java.util.List;

public interface PostService {

    // 게시글 전체 목록 조회
    List<PostListResponseDTO> getAllPosts(PostSearchFilterRequestDTO postSearchFilterRequestDTO);

    // 게시글 등록
    int addPost(PostRegisterRequestDTO request);

    // 게시글 상세 조회
    PostDetailResponseDTO findPost(int postId);

    // 게시글 검색 (키워드 기반)
    List<PostListResponseDTO> findPostByKeyword(String keyword);

    // 게시글 삭제
    int removePost(int postId);
}
