package com.otclub.humate.domain.mate.controller;

import com.otclub.humate.domain.mate.dto.PostDetailResponseDTO;
import com.otclub.humate.domain.mate.dto.PostListResponseDTO;
import com.otclub.humate.domain.mate.dto.PostRegisterRequestDTO;
import com.otclub.humate.domain.mate.dto.PostSearchFilterRequestDTO;
import com.otclub.humate.domain.mate.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 매칭글 컨트롤러
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
@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    /**
     * 매칭글 전체 목록 조회
     *
     * @author 김지현
     * @param postSearchFilterRequestDTO 검색 필터 정보를 담은 객체
     * @return 전체 매칭글 목록을 담은 ResponseEntity
     */
    @GetMapping("/list")
    public ResponseEntity<List<PostListResponseDTO>> postList(@ModelAttribute PostSearchFilterRequestDTO postSearchFilterRequestDTO) {
        List<PostListResponseDTO> postListResponseDTOs = postService.getAllPosts(postSearchFilterRequestDTO);
        log.info("[controller단] postListResponseDTOs -> " + postListResponseDTOs);
        log.info("gender -> " + postSearchFilterRequestDTO.getGender());
        return ResponseEntity.ok(postListResponseDTOs);
    }

    /**
     * 매칭글 등록
     *
     * @author 김지현
     * @param request 등록될 매칭글 정보를 담은 객체
     * @return 등록된 매칭글 postId를 담은 ResponseEntity
     */
    @PostMapping("/new")
    public ResponseEntity<Integer> postAdd(@RequestBody PostRegisterRequestDTO request) {
        int postId = postService.addPost(request);
        log.info("[controller단] post 등록 -> " + request.toString());
        return ResponseEntity.ok(postId);
    }

    /**
     * 매칭글 상세 정보 조회
     *
     * @author 김지현
     * @param postId 조회할 postId
     * @return 조회한 매칭글 정보를 담은 ResponseEntity
     */
    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponseDTO> postDetails(@PathVariable("postId") int postId) {
        PostDetailResponseDTO postDetailResponseDTO = postService.findPost(postId);
        return ResponseEntity.ok(postDetailResponseDTO);
    }

    /**
     * 매칭글 검색
     *
     * @author 김지현
     * @param keyword 검색할 keyword
     * @return 검색한 매칭글 목록을 담은 ResponseEntity
     */
    @GetMapping("/search")
    public ResponseEntity<List<PostListResponseDTO>> postSearchList(@RequestParam("keyword") String keyword) {
        List<PostListResponseDTO> postListResponseDTOs = postService.findPostByKeyword(keyword);
        log.info("[controller단] postSearch keyword -> " + keyword);
        return ResponseEntity.ok(postListResponseDTOs);
    }

    /**
     * 매칭글 삭제
     *
     * @author 김지현
     * @param postId 삭제할 매칭글 postId
     * @return 삭제된 매칭글 postId를 담은 ResponseEntity
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Integer> postRemove(@PathVariable("postId") int postId) {
        int deletedPostId = postService.removePost(postId);
        log.info("[controller단] post 삭제 -> " + deletedPostId);
        return ResponseEntity.ok(deletedPostId);
    }

}
