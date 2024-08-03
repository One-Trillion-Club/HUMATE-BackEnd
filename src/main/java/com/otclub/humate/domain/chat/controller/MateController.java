package com.otclub.humate.domain.chat.controller;


import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.chat.dto.MateCreateRequestDTO;
import com.otclub.humate.domain.chat.service.MateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mate")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MateController {
    private final MateService mateService;

    @PostMapping("/new")
    public ResponseEntity<CommonResponseDTO> mateModify(@RequestBody MateCreateRequestDTO request) {

        mateService.modifyMate(request);
        log.info("[MateController] - 메이트 업데이트 {}", request.toString());
        return ResponseEntity.ok(new CommonResponseDTO(true, "메이트 업데이트가 완료되었습니다."));
    }
}
