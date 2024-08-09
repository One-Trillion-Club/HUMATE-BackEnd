package com.otclub.humate.domain.companion.service;

import com.otclub.humate.domain.companion.dto.CompanionResponseDTO;

import java.util.List;

/**
 * 동행 service interface
 * @author 손승완
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	손승완        최초 생성
 * </pre>
 */
public interface CompanionService {
    void endCompanion(int companionId, String memberId);

    List<CompanionResponseDTO> findCompanionList(String memberId);

    void startCompanion(String chatRoomId, String memberId);
}
