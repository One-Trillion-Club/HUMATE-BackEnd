package com.otclub.humate.domain.mate.mapper;

import com.otclub.humate.common.entity.PostEntity;
import com.otclub.humate.common.entity.PostPlaceEntity;
import com.otclub.humate.common.entity.PostTagEntity;
import com.otclub.humate.domain.mate.dto.PostListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    // 매칭글 전체 목록 조회
    List<PostListResponseDTO> selectAllPosts();

    // 매칭글 등록
    int insertPost(PostEntity post);

    // 매장 및 팝업스토어 등록
    int insertPostPlace(PostPlaceEntity postPlace);

    // 태그 등록
    int insertPostTag(PostTagEntity postTag);
}
