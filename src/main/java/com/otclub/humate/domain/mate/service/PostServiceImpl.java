package com.otclub.humate.domain.mate.service;

import com.otclub.humate.common.entity.Post;
import com.otclub.humate.common.entity.PostPlace;
import com.otclub.humate.common.entity.PostTag;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.mate.dto.*;
import com.otclub.humate.domain.mate.mapper.PostMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    @Override
    @Transactional
    public List<PostListResponseDTO> getAllPosts(PostSearchFilterRequestDTO request) {
        // 1. 필수값 설정
        PostSearchFilterRequestDTO.PostSearchFilterRequestDTOBuilder preBuilder
                = PostSearchFilterRequestDTO.builder().gender(request.getGender());

        // 2. null값 (optional) 설정
        if (request.getTagName() != null) {
            log.info("null값이 아닌 tagName 설정");
            preBuilder.tagName(request.getTagName());
            preBuilder.tags(Arrays.asList(request.getTagName().split(",")));
        }

        if (request.getMatchDate() != null) {
            log.info("null값이 아닌 matchDate 설정");
            preBuilder.matchDate(request.getMatchDate());
        }

        if (request.getMatchBranch() != null) {
            log.info("null값이 아닌 matchBranch 설정");
            preBuilder.matchBranch(request.getMatchBranch());
        }

        if (request.getMatchGender() != null) {
            log.info("null값이 아닌 matchGender 설정");
            preBuilder.matchGender(request.getMatchGender());
        }

        if (request.getMatchLanguage() != null) {
            log.info("null값이 아닌 matchLanguage 설정");
            preBuilder.matchLanguage(request.getMatchLanguage());
        }

        if (request.getKeyword() != null) {
            log.info("null값이 아닌 keyword 설정");
            preBuilder.keyword(request.getKeyword());
        }

        PostSearchFilterRequestDTO postSearchFilterRequestDTO = preBuilder.build();
        log.info("request gender -> " + postSearchFilterRequestDTO.getGender());
        log.info("request tags -> " + postSearchFilterRequestDTO.getTags());
        log.info("request matchDate -> " + postSearchFilterRequestDTO.getMatchDate());
        log.info("request matchBranch -> " + postSearchFilterRequestDTO.getMatchBranch());
        log.info("request matchGender -> " + postSearchFilterRequestDTO.getMatchGender());
        log.info("request matchLanguage -> " + postSearchFilterRequestDTO.getMatchLanguage());
        log.info("request keyword -> " + postSearchFilterRequestDTO.getKeyword());

        List<PostListResponseDTO> result = postMapper.selectAllPosts(postSearchFilterRequestDTO);
        log.info("[service단] result -> " + result);
        return result;
    }

    @Override
    @Transactional
    public int addPost(PostRegisterRequestDTO request) {
        // post 등록
        // 1. 필수값 설정
        Post.PostBuilder postBuilder = Post.builder()
                .memberId(request.getMemberId())
                .title(request.getTitle())
                .content(request.getContent())
                .matchGender(request.getMatchGender());

        // 2. null값 (optional) 설정
        if (request.getMatchDate() != null) {
            log.info("null값이 아닌 date 설정");
            postBuilder.matchDate(request.getMatchDate());
        }

        if (request.getMatchBranch() != null) {
            log.info("null값이 아닌 branch 설정");
            postBuilder.matchBranch(request.getMatchBranch());
        }

        if (request.getMatchLanguage() != null) {
            log.info("null값이 아닌 language 설정");
            postBuilder.matchLanguage(request.getMatchLanguage());
        }

        Post post = postBuilder.build();

        log.info("post: " + post.toString());
        int postResult = postMapper.insertPost(post);
        log.info("[service단] postResult -> " + postResult);

        // 등록된 글 ID 반환
        int postId = post.getPostId();

        // post_place 등록
        List<PostPlaceRegisterRequestDTO> postPlaces = request.getPostPlaces();
        if (postPlaces != null) {
            log.info("[service단] postPlaceResult");
            for (PostPlaceRegisterRequestDTO place : postPlaces) {
                PostPlace postPlace
                        = PostPlace.builder()
                        .postId(postId)
                        .type(place.getType())
                        .name(place.getName())
                        .build();
                int postPlaceResult = postMapper.insertPostPlace(postPlace);
                log.info(String.valueOf(postPlaceResult));
            }
        }

        // post_tag 등록
        List<PostTagRegisterRequestDTO> postTags = request.getPostTags();
        if (postTags != null) {
            log.info("[service단] postTagResult");
            for (PostTagRegisterRequestDTO tag : postTags) {
                PostTag postTag
                        = PostTag.builder()
                        .postId(postId)
                        .tagId(tag.getTagId())
                        .build();
                int postTagResult = postMapper.insertPostTag(postTag);
                log.info(String.valueOf(postTagResult));
            }
        }

        return postId;
    }

    @Override
    public PostDetailResponseDTO findPost(int postId) {
        PostBasicDetailResponseDTO postBasic = postMapper.selectPostById(postId);
        List<PostTagDetailResponseDTO> postTags = postMapper.selectPostTagById(postId);
        List<PostPlaceDetailResponseDTO> postPlaces = postMapper.selectPostPlaceById(postId);

        PostDetailResponseDTO post
                = PostDetailResponseDTO.builder()
                .postId(postId)
                .memberId(postBasic.getMemberId())
                .title(postBasic.getTitle())
                .content(postBasic.getContent())
                .matchDate(postBasic.getMatchDate())
                .matchBranch(postBasic.getMatchBranch())
                .matchGender(postBasic.getMatchGender())
                .matchLanguage(postBasic.getMatchLanguage())
                .postTags(postTags)
                .postPlaces(postPlaces)
                .build();

        log.info("[service단] result -> " + post.toString());
        return post;
    }

    @Override
    public List<PostListResponseDTO> findPostByKeyword(String keyword) {
        List<PostListResponseDTO> result = postMapper.selectPostByKeyword(keyword);
        log.info("[service단] result -> " + result);
        return result;
    }

    @Override
    @Transactional
    public int removePost(int postId) {
        int result = postMapper.deletePost(postId);
        log.info("[service단] result -> " + result);
        if (result == 0) {
            throw new CustomException(ErrorCode.FAIL_TO_DELETE_POST);
        }
        return postId;
    }

}
