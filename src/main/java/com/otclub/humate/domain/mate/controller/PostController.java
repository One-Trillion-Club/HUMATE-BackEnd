package com.otclub.humate.domain.mate.controller;

import com.otclub.humate.domain.mate.dto.PostDetailResponseDTO;
import com.otclub.humate.domain.mate.dto.PostListResponseDTO;
import com.otclub.humate.domain.mate.dto.PostRegisterRequestDTO;
import com.otclub.humate.domain.mate.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/list")
    public ResponseEntity<List<PostListResponseDTO>> postList() {
        List<PostListResponseDTO> postListResponseDTOs = postService.getAllPosts();
        log.info("[controller단] postListResponseDTOs -> " + postListResponseDTOs);
        return ResponseEntity.ok(postListResponseDTOs);
    }

    @PostMapping("/new")
    public ResponseEntity<Integer> postAdd(@RequestBody PostRegisterRequestDTO request) {
        int postId = postService.addPost(request);
        log.info("[controller단] post 등록 -> " + request.toString());
        return ResponseEntity.ok(postId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponseDTO> postDetails(@PathVariable("postId") int postId) {
        PostDetailResponseDTO postDetailResponseDTO = postService.findPost(postId);
        return ResponseEntity.ok(postDetailResponseDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostListResponseDTO>> postSearchList(@RequestParam("keyword") String keyword) {
        List<PostListResponseDTO> postListResponseDTOs = postService.findPostByKeyword(keyword);
        log.info("[controller단] postSearch keyword -> " + keyword);
        return ResponseEntity.ok(postListResponseDTOs);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Integer> postRemove(@PathVariable("postId") int postId) {
        int deletedPostId = postService.removePost(postId);
        log.info("[controller단] post 삭제 -> " + deletedPostId);
        return ResponseEntity.ok(deletedPostId);
    }

}
