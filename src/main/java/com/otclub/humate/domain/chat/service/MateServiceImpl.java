package com.otclub.humate.domain.chat.service;

import com.otclub.humate.common.entity.Member;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.chat.dto.ChatMessageRedisDTO;
import com.otclub.humate.domain.chat.dto.MateCreateRequestDTO;
import com.otclub.humate.domain.chat.mapper.MateMapper;
import com.otclub.humate.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MateServiceImpl implements MateService {
    private final MateMapper mateMapper;
    private final MemberMapper memberMapper;
    private final RedisPubSubService redisPubSubService;

    @Override
    public void modifyMate(MateCreateRequestDTO requestDTO) {
        // 회원 조회하기
        Member member = memberMapper.selectMemberDetail(requestDTO.getMemberId());
        log.info("[MateServiceImpl] - member : {}", member.toString());
        if (member == null) {
            throw new CustomException(ErrorCode.FORBIDDEN_REQUEST);
        }

        // 메이트 신청하기
        if (mateMapper.updateMate(requestDTO) == 0){
            throw new CustomException(ErrorCode.FORBIDDEN_REQUEST);
        }

        // 메이트 안내 채팅 전송하기
        ChatMessageRedisDTO redisDTO = ChatMessageRedisDTO.ofMateActive(requestDTO, member.getNickname());
        redisPubSubService.pubSendMessageChannel(redisDTO);
    }
}
