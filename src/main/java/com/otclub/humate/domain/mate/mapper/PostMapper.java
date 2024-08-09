package com.otclub.humate.domain.mate.mapper;

import com.otclub.humate.common.entity.Post;
import com.otclub.humate.common.entity.PostPlace;
import com.otclub.humate.common.entity.PostTag;
import com.otclub.humate.domain.mate.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 매칭글 매퍼 인터페이스
 * @author 김지현
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	김지현        최초 생성
 * 2024.07.31   김지현        매칭글 전체 목록 조회 메서드 추가
 * 2024.08.07   최유경        postId를 사용한 매칭글 검색 메서드 추가
 * </pre>
 */
@Mapper
public interface PostMapper {

    // 매칭글 전체 목록 조회
    List<PostListResponseDTO> selectAllPosts(PostSearchFilterRequestDTO postSearchFilterRequestDTO);

    // 매칭글 등록
    int insertPost(Post post);

    // 매장 및 팝업스토어 등록
    int insertPostPlace(PostPlace postPlace);

    // 태그 등록
    int insertPostTag(PostTag postTag);

    // 매칭글 상세 조회
    PostBasicDetailResponseDTO selectPostById(int postId);

    // 매칭글 장소(매장 및 팝업스토어) 상세 조회
    List<PostPlaceDetailResponseDTO> selectPostPlaceById(int postId);

    // 매칭글 태그 상세 조회
    List<PostTagDetailResponseDTO> selectPostTagById(int postId);

    // postId를 사용한 매칭글 존재 확인
    boolean selectPostCountById(int postId);

    // 매칭글 검색
    List<PostListResponseDTO> selectPostByKeyword(String keyword);

    // 매칭글 삭제
    int deletePost(int postId);

    // postId를 사용한 매칭글 검색
    Post selectPostByPostId(int postId);
}
