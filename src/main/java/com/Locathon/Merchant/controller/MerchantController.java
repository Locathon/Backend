package com.Locathon.Merchant.controller;

import com.Locathon.Merchant.dto.*;
import com.Locathon.Merchant.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    // 스타일 변환
    @PostMapping("/merchant/style-transform")
    public ResponseEntity<StyleTransformResponse> styleTransform(@RequestBody StyleTransformRequest request) {
        return ResponseEntity.ok(merchantService.styleTransform(request));
    }

    // 스타일 변환 로그 조회
    @GetMapping("/merchant/style-log")
    public ResponseEntity<List<StyleTransformLogDto>> getStyleTransformLogs() {
        return ResponseEntity.ok(merchantService.getStyleTransformLogs());
    }

    // 상점 QA 추가
    @PostMapping("/qa/add")
    public ResponseEntity<?> addQa(@RequestParam String merchant_id, @RequestBody MerchantQaRequest request) {
        merchantService.addMerchantQa(merchant_id, request);
        return ResponseEntity.ok(java.util.Map.of("message", "QA entry added successfully."));
    }

    // 상점 QA 수정
    @PutMapping("/qa/edit")
    public ResponseEntity<?> editQa(@RequestParam String merchant_id, @RequestBody MerchantQaRequest request) {
        merchantService.editMerchantQa(merchant_id, request);
        return ResponseEntity.ok(java.util.Map.of("message", "QA entry updated successfully."));
    }

    // 상점 QA 삭제
    @DeleteMapping("/qa/delete")
    public ResponseEntity<?> deleteQa(@RequestParam String merchant_id, @RequestParam String question) {
        merchantService.deleteMerchantQa(merchant_id, question);
        return ResponseEntity.ok(java.util.Map.of("message", "QA entry deleted successfully."));
    }

    // 상점 QA 목록 조회
    @GetMapping("/qa/list")
    public ResponseEntity<List<MerchantQaResponse>> listQa(@RequestParam String merchant_id) {
        return ResponseEntity.ok(merchantService.getMerchantQaList(merchant_id));
    }
}
