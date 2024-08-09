package com.otclub.humate.domain.companion.service;

import com.otclub.humate.domain.companion.dto.CompanionResponseDTO;

import java.util.List;

/**
 * 동행 service 인터페이스
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * </pre>
 */
public interface CompanionService {

    /**
     * 동행 종료
     *
     * @author 손승완
     * @param companionId
     * @param memberId
     */
    void endCompanion(int companionId, String memberId);

    /**
     * 동행 목록 조회
     *
     * @author 손승완
     * @param memberId
     * @return
     */
    List<CompanionResponseDTO> findCompanionList(String memberId);

    /**
     * 동행 시작
     *
     * @author 손승완
     * @param chatRoomId
     * @param memberId
     */
    void startCompanion(String chatRoomId, String memberId);
}
