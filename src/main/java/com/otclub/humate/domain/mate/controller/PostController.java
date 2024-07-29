package com.otclub.humate.domain.mate.controller;

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

}
