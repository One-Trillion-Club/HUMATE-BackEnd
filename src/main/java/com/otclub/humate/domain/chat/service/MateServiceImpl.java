package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.entity.ChatParticipate;
import com.otclub.humate.common.entity.Member;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.dto.MateCreateRequestDTO;
import com.otclub.humate.domain.chat.mapper.ChatRoomMapper;
import com.otclub.humate.domain.chat.mapper.MateMapper;
import com.otclub.humate.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 메이트 관련 서비스
 * @author 최유경
 * @since 2024.08.03
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.03  	최유경        최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MateServiceImpl implements MateService {
    private final MateMapper mateMapper;
    private final ChatRoomMapper chatRoomMapper;
    private final MemberMapper memberMapper;
    private final RedisPubSubService redisPubSubService;

    @Override
    public void modifyMate(MateCreateRequestDTO requestDTO) {
        // 회원 조회하기
        Member member = memberMapper.selectMemberDetail(requestDTO.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        log.info("[MateServiceImpl] - member : {}", member.toString());

        // 채팅방에 참여한 회원인지 확인하기
        ChatParticipate chatParticipate = chatRoomMapper.selectChatParticipantByChatRoomIdAndMemberId(requestDTO.getChatRoomId(), requestDTO.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode.CHAT_PARTICIPATE_NOT_FOUND));
        log.info("[[MateServiceImpl] - chatParticipate : {} ", chatParticipate.toString());

        // 메이트 신청하기
        if (mateMapper.updateMate(requestDTO) == 0){
            throw new CustomException(ErrorCode.FORBIDDEN_REQUEST);
        }

        // 메이트 안내 채팅 전송하기
        ChatMessageRedisDTO redisDTO = ChatMessageRedisDTO.ofMateActive(requestDTO, member.getNickname());
        redisPubSubService.pubSendMessageChannel(redisDTO);
    }
}
