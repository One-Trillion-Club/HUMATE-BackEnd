package com.otclub.humate.domain.chat.service;

import com.otclub.humate.domain.chat.dto.RoomCreateRequestDTO;
import com.otclub.humate.domain.chat.dto.RoomCreateResponseDTO;
import com.otclub.humate.domain.chat.dto.RoomDetailDTO;
import java.util.List;

/**
 * 채팅방 서비스
 * @author 최유경
 * @since 2024.07.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.31  	최유경        최초 생성
 * 2024.08.03   최유경        채팅방 리스트 조회
 * </pre>
 */
public interface RoomService {
    /**
     * 채팅방 생성
     *
     * @author 최유경
     * @param memberId 요청 회원 ID
     * @param requestDTO 요청 글 정보
     * @return RoomCreateResponseDTO 채팅방 생성 응답
     */
    RoomCreateResponseDTO createChatRoom(String memberId, RoomCreateRequestDTO requestDTO); // 채팅방 생성

    /**
     * 메이트 채팅/대기 목록 조회
     *
     * @author 최유경
     * @param memberId 요청 회원 ID
     * @param isMatched 상태값 (0-매칭안됨, 1-매칭됨)
     * @return List 메이트 채팅/대기 목록 리스트
     */
    List<RoomDetailDTO> findChatRoomDetailList(String memberId, int isMatched);

    /**
     * 채팅방 상세 내용 조회
     *
     * @author 최유경
     * @param participateId 참여 회원 ID
     * @return RoomDetailDTO 상세 DTO
     */
    RoomDetailDTO findChatRoomDetail(String participateId);
}
