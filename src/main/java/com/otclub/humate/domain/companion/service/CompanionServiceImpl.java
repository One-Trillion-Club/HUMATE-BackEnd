package com.otclub.humate.domain.companion.service;

import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.companion.dto.CompanionDetailsDTO;
import com.otclub.humate.domain.companion.dto.CompanionResponseDTO;
import com.otclub.humate.domain.companion.mapper.CompanionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanionServiceImpl implements CompanionService {
    private final CompanionMapper companionMapper;

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
}
