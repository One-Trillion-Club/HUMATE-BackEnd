package com.otclub.humate.domain.companion.service;

import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.companion.mapper.CompanionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanionServiceImpl implements CompanionService {
    private final CompanionMapper companionMapper;

    @Override
    public void endCompanion(int companionId) {
        int updatedRowCnt = companionMapper.updateCompanionStatusById(companionId);
        if (updatedRowCnt != 1) {
            throw new CustomException(ErrorCode.CANCEL_COMPANION_FAIL);
        }

    }
}
