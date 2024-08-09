package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.Member;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.dto.MessageRedisDTO;
import com.otclub.humate.domain.chat.dto.MateUpdateRequestDTO;
import com.otclub.humate.domain.chat.mapper.RoomMapper;
import com.otclub.humate.domain.chat.mapper.MateMapper;
import com.otclub.humate.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 메이트 관련 서비스 구현체
 * @author 최유경
 * @since 2024.08.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  	최유경        최초 생성
 * 2024.08.05   최유경        회원 조회 추가
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MateServiceImpl implements MateService {
    private final MateMapper mateMapper;
    private final RoomMapper roomMapper;
    private final MemberMapper memberMapper;
    private final MessageService messageService;

    /**
     * 메이트 맺기 신청/취소
     *
     * @author 최유경
     * @param requestDTO 메이트 신청 참여자 정보
     *
     * @exception CustomException CHAT_PARTICIPATE_NOT_FOUND, MEMBER_NOT_FOUND, CHAT_MATE_CLICK_CONFLICT, FORBIDDEN_REQUEST
     */
    @Override
    public void modifyMate(MateUpdateRequestDTO requestDTO) {
        // 채팅방에 참여한 회원인지 확인하기
        ChatParticipate chatParticipate = roomMapper.selectChatParticipantByParticipateId(requestDTO.getParticipateId())
                .orElseThrow(() -> new CustomException(ErrorCode.CHAT_PARTICIPATE_NOT_FOUND));
        log.info("[[MateServiceImpl] - chatParticipate : {} ", chatParticipate.toString());

        // 회원 조회하기
        Member member = memberMapper.selectMemberDetail(chatParticipate.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        log.info("[MateServiceImpl] - member : {}", member.toString());

        // 이미 처리된 내용에 대해 예외 처리
        if(chatParticipate.getIsClicked() == requestDTO.getIsClicked())
            throw new CustomException(ErrorCode.CHAT_MATE_CLICK_CONFLICT);

        // 메이트 신청하기
        if (mateMapper.updateMate(requestDTO) == 0){
            throw new CustomException(ErrorCode.FORBIDDEN_REQUEST);
        }

        int cnt = roomMapper.selectParticipatesClickCount(requestDTO.getChatRoomId());
        log.info("[MateServiceImpl] - selectParticipatesClickCount {} ", cnt);

        if(roomMapper.selectParticipatesClickCount(requestDTO.getChatRoomId())==2){
            log.info("[MateServiceImpl] - 들어옴  ");
            int status = roomMapper.updateChatRoomStatus(requestDTO.getChatRoomId());
            log.info("[MateServiceImpl] - updateChatRoomStatus {} ", status);
        }

        // 메이트 안내 채팅 전송하기
        MessageRedisDTO redisDTO = MessageRedisDTO.ofMate(requestDTO, member.getNickname());
        messageService.createMessage(redisDTO);

    }
}
