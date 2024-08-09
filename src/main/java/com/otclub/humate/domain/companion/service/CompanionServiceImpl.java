package com.otclub.humate.domain.companion.service;

import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.mapper.ChatRoomMapper;
import com.otclub.humate.domain.companion.dto.CompanionDTO;
import com.otclub.humate.domain.companion.dto.CompanionDetailsDTO;
import com.otclub.humate.domain.companion.dto.CompanionResponseDTO;
import com.otclub.humate.domain.companion.mapper.CompanionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 동행 service 구현체
 * @author 손승완
 * @since 2024.07.30
 * @version 1.1
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * 2024.08.06   손승완        동행 시작 기능 추가
 *
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CompanionServiceImpl implements CompanionService {
    private final CompanionMapper companionMapper;
    private final ChatRoomMapper chatRoomMapper;

    /**
     * 동행 종료
     *
     * @author 손승완
     * @param companionId
     * @param memberId
     * @exception CustomException NOT_EXISTS_COMPANION, CANCEL_COMPANION_FAIL
     */
    @Override
    @Transactional(readOnly = true)
    public void endCompanion(int companionId, String memberId) {
        if (companionMapper.countCompanionByMemberIdAndCompanionId(memberId, companionId) == 0) {
            throw new CustomException(ErrorCode.NOT_EXISTS_COMPANION);
        }


        int updatedRowCnt = companionMapper.updateCompanionStatusById(companionId);
        if (updatedRowCnt != 1) {
            throw new CustomException(ErrorCode.CANCEL_COMPANION_FAIL);
        }

    }

    /**
     * 동행 목록 조회
     *
     * @author 손승완
     * @param memberId
     * @return
     */
    @Override
    public List<CompanionResponseDTO> findCompanionList(String memberId) {
        List<CompanionDetailsDTO> companionDetailsList = companionMapper.selectCompanionListByMemberId(memberId);
        return CompanionResponseDTO.ofList(companionDetailsList, memberId);
    }

    /**
     * 동행 시작
     *
     * @author 손승완
     * @param chatRoomId
     * @param memberId
     * @exception CustomException FORBIDDEN_REQUEST, FAILED_COMPANION_START
     */
    @Override
    @Transactional
    public void startCompanion(String chatRoomId, String memberId) {
        List<String> members = chatRoomMapper.selectChatRoomMemberById(Integer.parseInt(chatRoomId));
        log.info("[startCompanion] - {}" , memberId);
        if (!members.contains(memberId)) {
            log.error(ErrorCode.FORBIDDEN_REQUEST.getMessage());
            throw new CustomException(ErrorCode.FORBIDDEN_REQUEST);
        }


        CompanionDTO companion = CompanionDTO.of(Integer.parseInt(chatRoomId),
                                                members.get(0),
                                                members.get(1));

        if (companionMapper.insertCompanion(companion) != 1) {
            log.error(ErrorCode.FAILED_COMPANION_START.getMessage());
            throw new CustomException(ErrorCode.FAILED_COMPANION_START);
        }
    }
}
