package com.otclub.humate.domain.companion.service;

import com.otclub.humate.domain.companion.dto.CompanionResponseDTO;

import java.util.List;

public interface CompanionService {
    void endCompanion(int companionId, String memberId);

    List<CompanionResponseDTO> findCompanionList(String memberId);

    void startCompanion(String chatRoomId, String memberId);
}
