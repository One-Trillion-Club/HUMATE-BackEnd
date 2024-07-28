package com.otclub.humate.domain.mate.controller;

import com.otclub.humate.domain.mate.dto.PostListResponseDTO;
import com.otclub.humate.domain.mate.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/post")
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
}
