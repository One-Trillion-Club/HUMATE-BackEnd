package com.otclub.humate.domain.companion.controller;

import com.otclub.humate.common.annotation.MemberId;
import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.chat.dto.ChatRoomRequestDTO;
import com.otclub.humate.domain.companion.dto.CompanionResponseDTO;
import com.otclub.humate.domain.companion.service.CompanionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 동행 controller
 * @author 손승완
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	손승완        최초 생성
 * 2024.08.06   손승완        동행 시작 기능 추가
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/companions")
@Slf4j
public class CompanionController {
    private final CompanionService companionService;

    /**
     *
     * 동행 목록 조회
     *
     * @author 손승완
     * @param memberId
     * @return
     */
    @GetMapping
    public ResponseEntity<List<CompanionResponseDTO>> companionList(@MemberId String memberId) {
        return ResponseEntity.ok(companionService.findCompanionList(memberId));
    }

    /**
     * 동행 시작
     *
     *
     * @author 손승완
     * @param chatRoomId
     * @param memberId
     * @return
     */
    @PostMapping("/start")
    public ResponseEntity<CommonResponseDTO> companionStart(@RequestBody ChatRoomRequestDTO chatRoomId,
                                                            @MemberId String memberId) {
        companionService.startCompanion(chatRoomId.getChatRoomId(), memberId);
        log.error("[companionStart] {}", "완료");
        return ResponseEntity.ok(new CommonResponseDTO(true, "동행이 시작되었습니다."));
    }

    /**
     * 동행 종료
     *
     * @author 손승완
     * @param companionId
     * @param memberId
     * @return
     */
    @DeleteMapping ("/finish")
    public ResponseEntity<CommonResponseDTO> companionFinish(@RequestParam("companionId") int companionId,
                                                             @MemberId String memberId) {
        // 동행 종료 -> 종료일 현재 시간으로 갱신
        companionService.endCompanion(companionId, memberId);

        return ResponseEntity.ok(new CommonResponseDTO(true, "동행이 종료되었습니다."));
    }

}
