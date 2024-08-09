package com.otclub.humate.domain.chat.controller;


import com.otclub.humate.common.dto.CommonResponseDTO;
import com.otclub.humate.domain.chat.dto.MateUpdateRequestDTO;
import com.otclub.humate.domain.chat.service.MateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 메이트 신청,조회,취소 관련 컨트롤러
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
@RequestMapping("/mate")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MateController {
    private final MateService mateService;

    /**
     * 메이트 맺기 신청/취소
     *
     * @author 최유경
     * @param request 메이트 맺기 위한 정보
     */
    @PutMapping("/update")
    public ResponseEntity<CommonResponseDTO> mateModify(@RequestBody MateUpdateRequestDTO request) {

        mateService.modifyMate(request);
        log.info("[MateController] - 메이트 업데이트 {}", request.toString());
        return ResponseEntity.ok(new CommonResponseDTO(true, "메이트 업데이트가 완료되었습니다."));
    }
}
