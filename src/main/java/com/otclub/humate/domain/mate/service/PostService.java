package com.otclub.humate.domain.mate.service;

import com.otclub.humate.domain.mate.dto.PostDetailResponseDTO;
import com.otclub.humate.domain.mate.dto.PostListResponseDTO;
import com.otclub.humate.domain.mate.dto.PostRegisterRequestDTO;
import com.otclub.humate.domain.mate.dto.PostSearchFilterRequestDTO;

import java.util.List;

/**
 * 매칭글 서비스 인터페이스
 * @author 김지현
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	김지현        최초 생성
 * 2024.07.31   김지현        매칭글 전체 목록 조회 메서드 추가
 * </pre>
 */
public interface PostService {

    /**
     * 전체 매칭글 조회
     *
     * @author 김지현
     * @param postSearchFilterRequestDTO 검색 및 필터링 정보를 담은 객체
     * @return 조회한 매칭글 목록을 담은 list
     */
    List<PostListResponseDTO> getAllPosts(PostSearchFilterRequestDTO postSearchFilterRequestDTO);

    /**
     * 매칭글 등록
     *
     * @author 김지현
     * @param request 등록될 매칭글 정보를 담은 객체
     * @return 등록된 매칭글 postId
     */
    int addPost(PostRegisterRequestDTO request);

    /**
     * 매칭글 상세 정보 조회
     *
     * @author 김지현
     * @param postId 조회할 postId
     * @return 조회한 매칭글 정보
     */
    PostDetailResponseDTO findPost(int postId);

    /**
     * 매칭글 검색
     *
     * @author 김지현
     * @param keyword 검색할 keyword
     * @return 검색한 매칭글 목록을 담은 list
     */
    List<PostListResponseDTO> findPostByKeyword(String keyword);

    /**
     * 매칭글 삭제
     *
     * @author 김지현
     * @param postId 삭제할 매칭글 postId
     * @return 삭제된 매칭글 postId
     */
    int removePost(int postId);
}
