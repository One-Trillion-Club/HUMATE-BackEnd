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
import java.util.stream.Collectors;

/**
 * 매칭글 서비스 구현체
 * @author 김지현
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ----------------------------------------
 * 2024.07.30  	김지현        최초 생성
 * 2024.07.31   김지현        매칭글 전체 목록 조회 메서드 추가
 * 2024.08.02   김지현        매칭글 전체 목록 조회 메서드에서 국적 필터링 추가
 * 2024.08.03   김지현        매칭글 상세 정보 조회 메서드에서 이미지 프로필 추가
 * </pre>
 */
@Slf4j
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    /**
     * 전체 매칭글 조회
     *
     * @author 김지현
     * @param request 검색 및 필터링 정보를 담은 객체
     * @return 조회한 매칭글 목록을 담은 list
     */
    @Override
    @Transactional
    public List<PostListResponseDTO> getAllPosts(PostSearchFilterRequestDTO request) {
        // 1. 필수값 설정
        PostSearchFilterRequestDTO.PostSearchFilterRequestDTOBuilder preBuilder
                = PostSearchFilterRequestDTO.builder()
                    .gender(request.getGender())
                    .memberId(request.getMemberId());

        // 2. null값 (optional) 설정
        if (request.getTagName() != null) {
            log.info("null값이 아닌 tagName 설정");
            preBuilder.tagName(request.getTagName());
            List<String> tags = Arrays.stream(request.getTagName().split(","))
                    .map(String::trim)
                    .filter(tag -> !tag.isEmpty())
                    .collect(Collectors.toList());
            log.info("받은 tags -> " + tags);
            preBuilder.tags(tags);
            preBuilder.tagCount(tags.size());
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
        log.info("request tagCount -> " + postSearchFilterRequestDTO.getTagCount());

        List<PostListResponseDTO> result = postMapper.selectAllPosts(postSearchFilterRequestDTO);
        log.info("[service단] result -> " + result);
        return result;
    }

    /**
     * 매칭글 등록
     *
     * @author 김지현
     * @param request 등록될 매칭글 정보를 담은 객체
     * @return 등록된 매칭글 postId
     */
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

    /**
     * 매칭글 상세 정보 조회
     *
     * @author 김지현
     * @param postId 조회할 postId
     * @return 조회한 매칭글 정보
     */
    @Override
    public PostDetailResponseDTO findPost(int postId) {
        PostBasicDetailResponseDTO postBasic = postMapper.selectPostById(postId);
        List<PostTagDetailResponseDTO> postTags = postMapper.selectPostTagById(postId);
        List<PostPlaceDetailResponseDTO> postPlaces = postMapper.selectPostPlaceById(postId);

        PostDetailResponseDTO post
                = PostDetailResponseDTO.builder()
                .postId(postId)
                .memberId(postBasic.getMemberId())
                .profileImgUrl(postBasic.getProfileImgUrl())
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

    /**
     * 매칭글 검색
     *
     * @author 김지현
     * @param keyword 검색할 keyword
     * @return 검색한 매칭글 목록을 담은 list
     */
    @Override
    public List<PostListResponseDTO> findPostByKeyword(String keyword) {
        List<PostListResponseDTO> result = postMapper.selectPostByKeyword(keyword);
        log.info("[service단] result -> " + result);
        return result;
    }

    /**
     * 매칭글 삭제
     *
     * @author 김지현
     * @param postId 삭제할 매칭글 postId
     * @return 삭제된 매칭글 postId
     * @exception CustomException FAIL_TO_DELETE_POST
     */
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
