package com.otclub.humate.domain.companion.service;

import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.mapper.ChatRoomMapper;
import com.otclub.humate.domain.companion.dto.CompanionDTO;
import com.otclub.humate.domain.companion.dto.CompanionDetailsDTO;
import com.otclub.humate.domain.companion.dto.CompanionResponseDTO;
import com.otclub.humate.domain.companion.mapper.CompanionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanionServiceImpl implements CompanionService {
    private final CompanionMapper companionMapper;
    private final ChatRoomMapper chatRoomMapper;

    @Override
    public void endCompanion(int companionId, String memberId) {
        if (companionMapper.countCompanionByMemberIdAndCompanionId(memberId, companionId) == 0) {
            throw new CustomException(ErrorCode.NOT_EXISTS_COMPANION);
        }


        int updatedRowCnt = companionMapper.updateCompanionStatusById(companionId);
        if (updatedRowCnt != 1) {
            throw new CustomException(ErrorCode.CANCEL_COMPANION_FAIL);
        }

    }

    @Override
    public List<CompanionResponseDTO> findCompanionList(String memberId) {
        List<CompanionDetailsDTO> companionDetailsList = companionMapper.selectCompanionListByMemberId(memberId);
        return CompanionResponseDTO.ofList(companionDetailsList, memberId);
    }

    @Override
    @Transactional
    public void startCompanion(String chatRoomId, String memberId) {
        List<String> members = chatRoomMapper.selectChatRoomMemberById(Integer.parseInt(chatRoomId));
        if (!members.contains(memberId)) {
            throw new CustomException(ErrorCode.FORBIDDEN_REQUEST);
        }

        CompanionDTO companion = CompanionDTO.of(Integer.parseInt(chatRoomId),
                                                members.get(0),
                                                members.get(1));

        if (companionMapper.insertCompanion(companion) != 1) {
            throw new CustomException(ErrorCode.FAILED_COMPANION_START);
        }

    }
}
