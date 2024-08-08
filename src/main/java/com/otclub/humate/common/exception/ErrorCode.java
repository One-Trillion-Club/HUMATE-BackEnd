package com.otclub.humate.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ErrorCode 관리 Enum 클래스
 * @author 손승완
 * @since 2024.07.26
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.26   손승완        최초 생성
 * </pre>
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_KEY(409, "중복되는 아이디나 닉네임이 존재합니다. 다시 시도해주세요."),
    UPLOAD_FAIL(400, "사진 업로드에 실패했습니다."),
    ALREADY_EXISTS_ACTIVITY(409, "이미 등록한 활동이거나 동행이 종료되었습니다. 다른 활동을 등록해 주세요."),
    CANCEL_COMPANION_FAIL(400, "동행 취소에 실패했습니다."),
    FAILED_COMPANION_START(400, "동행을 시작할 수 없습니다"),
    NOT_EXISTS_COMPANION(400, "존재하지 않는 동행입니다."),
    REVIEW_FAIL(400, "리뷰 등록에 실패했습니다."),
    POST_NOT_FOUND(404, "해당 매칭글을 찾을 수 없습니다."),
    NOT_VALID_USER_INFORMATION(400, "아이디나 비밀번호가 올바르지 않습니다."),
    FAIL_TO_DELETE_POST(400, "존재하지 않는 게시물이거나 이미 삭제된 게시물입니다."),
    FORBIDDEN_REQUEST(403, "접근할 권한이 없습니다."),
    UNEXPECTED_EXCEPTION(500, "예상하지 못한 오류가 발생했습니다. 다시 한 번 시도해주세요."),
    ALREADY_EXISTS_PHONE(409, "이미 가입된 휴대폰 번호입니다."),
    MEMBER_NOT_FOUND(404, "존재하지 않은 유저입니다."),
    CHAT_PARTICIPATE_NOT_FOUND(404, "해당 채팅방에 참여하지 않은 유저입니다."),
    CHAT_MATE_CLICK_CONFLICT(409, "이미 메이트 버튼이 처리되었습니다."),
    NOT_VALID_INPUT(400, "입력값이 올바르지 않습니다."),
    VERIFICATION_INVALID(400, "인증 정보가 존재하지 않습니다."),
    NOT_EXISTS_MEMBER(400, "존재하지 않는 회원입니다.");

    private final int status;
    private final String message;
}
