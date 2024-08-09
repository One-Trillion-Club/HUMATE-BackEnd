package com.otclub.humate.common.entity;

import jakarta.annotation.Nullable;
import lombok.*;

import java.util.Date;

/**
 * 매칭글 Entity
 * @author 김지현
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	김지현        최초 생성
 * </pre>
 */
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Builder
@Getter
public class Post {

    // 매칭글 ID
    private int postId;
    // 회원 ID
    private String memberId;
    // 매칭글 제목
    private String title;
    // 매칭글 내용
    private String content;
    // 매칭 날짜
    private String matchDate;
    // 매칭 지점
    private String matchBranch;
    // 매칭 성별
    private int matchGender;
    // 매칭 언어
    private String matchLanguage;
    // 작성 날짜
    private Date createdAt;
    // 삭제 날짜
    private Date deletedAt;
    // 매칭 여부
    private int isMatched;
}
