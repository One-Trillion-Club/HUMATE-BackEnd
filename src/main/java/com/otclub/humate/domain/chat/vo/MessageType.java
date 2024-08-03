package com.otclub.humate.domain.chat.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 채팅메세지 타입 Enum
 * @author 최유경
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	최유경        최초 생성
 * </pre>
 */
@Getter
@RequiredArgsConstructor
public enum MessageType {
    TEXT(0 , ""),
    IMAGE(1, ""),
    ENTER(2, " 님이 입장했습니다."),
    EXIT(3, " 님이 나갔습니다."),
    MATE_ACTIVE(4, " 님이 메이트 신청을 했습니다."),
    MATE_INACTIVE(5, " 님이 메이트 신청을 취소했습니다.");

    private final Integer type;
    private final String msg;
}
