package com.otclub.humate.domain.mate.service;

import com.otclub.humate.domain.mate.dto.PostListResponseDTO;
import com.otclub.humate.domain.mate.mapper.PostMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    @Override
    @Transactional
    public List<PostListResponseDTO> getAllPosts() {
        List<PostListResponseDTO> result = postMapper.selectAllPosts();
        log.info("[service단] result -> " + result);
        return result;
    }
}
