package com.otclub.humate.domain.companion.controller;

import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.companion.dto.CompanionResponseDTO;
import com.otclub.humate.domain.companion.service.CompanionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companions")
public class CompanionController {
    private final CompanionService companionService;

    @DeleteMapping ("/finish")
    public ResponseEntity<CommonResponseDTO> companionFinish(@RequestParam("companionId") int companionId) {
        // 접속한 회원이 참여 중인 동행에 한해서 종료가능해야한다.(검증 로직 추가 필요)

        // 동행 종료 -> 종료일 현재 시간으로 갱신
        companionService.endCompanion(companionId);

        return ResponseEntity.ok(new CommonResponseDTO(true, "동행이 종료되었습니다."));
    }

    /**
     * 동행 목록 조회
     * @author : 큰모래
     */
    @GetMapping
    public ResponseEntity<List<CompanionResponseDTO>> companionList() {
        String memberId = "K_1";
        return ResponseEntity.ok(companionService.findCompanionList(memberId));
    }

}
