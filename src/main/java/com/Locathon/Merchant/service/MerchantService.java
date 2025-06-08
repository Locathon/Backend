package com.Locathon.Merchant.service;

import com.Locathon.Merchant.dto.*;
import com.Locathon.Merchant.repository.MerchantQaRepository;
import com.Locathon.Merchant.repository.StyleTransformLogRepository;
import com.Locathon.model.MerchantQa;
import com.Locathon.model.StyleTransformLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final StyleTransformLogRepository styleTransformLogRepository;
    private final MerchantQaRepository merchantQaRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String AI_SERVER_BASE_URL = "http://localhost:8000";
ls
    // 스타일 변환 요청 및 저장
    public StyleTransformResponse styleTransform(StyleTransformRequest request) {
        String url = AI_SERVER_BASE_URL + "/merchant/style-transform";

        // AI 서버 호출용 요청 맵핑
        var aiRequest = new java.util.HashMap<String, Object>();
        aiRequest.put("original_text", request.getOriginalText());
        if (request.getTone() != null && !request.getTone().isEmpty()) {
            aiRequest.put("tone", request.getTone());
        }

        var aiResponse = restTemplate.postForObject(url, aiRequest, java.util.Map.class);

        String original = (String) aiResponse.get("original");
        String transformed = (String) aiResponse.get("transformed");

        // DB에 기록 저장
        StyleTransformLog log = StyleTransformLog.builder()
                .originalText(original)
                .transformedText(transformed)
                .build();
        styleTransformLogRepository.save(log);

        return StyleTransformResponse.builder()
                .original(original)
                .transformed(transformed)
                .build();
    }

    // 스타일 변환 로그 리스트 조회
    public List<StyleTransformLogDto> getStyleTransformLogs() {
        return styleTransformLogRepository.findAll().stream()
                .map(log -> StyleTransformLogDto.builder()
                        .timestamp(log.getTimestamp())
                        .original(log.getOriginalText())
                        .transformed(log.getTransformedText())
                        .build())
                .collect(Collectors.toList());
    }

    // 상점 QA 추가
    public void addMerchantQa(String merchantId, MerchantQaRequest request) {
        MerchantQa existing = merchantQaRepository.findByMerchantIdAndQuestion(merchantId, request.getQuestion());
        if (existing != null) {
            throw new IllegalStateException("이미 존재하는 질문입니다.");
        }

        MerchantQa qa = MerchantQa.builder()
                .merchantId(merchantId)
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .category(request.getCategory())
                .build();
        merchantQaRepository.save(qa);
    }

    // 상점 QA 수정
    public void editMerchantQa(String merchantId, MerchantQaRequest request) {
        MerchantQa existing = merchantQaRepository.findByMerchantIdAndQuestion(merchantId, request.getQuestion());
        if (existing == null) {
            throw new IllegalArgumentException("존재하지 않는 질문입니다.");
        }

        existing.setAnswer(request.getAnswer());
        existing.setCategory(request.getCategory());

        merchantQaRepository.save(existing);
    }

    // 상점 QA 삭제
    public void deleteMerchantQa(String merchantId, String question) {
        MerchantQa existing = merchantQaRepository.findByMerchantIdAndQuestion(merchantId, question);
        if (existing == null) {
            throw new IllegalArgumentException("존재하지 않는 질문입니다.");
        }

        merchantQaRepository.delete(existing);
    }

    // 상점 QA 목록 조회
    public List<MerchantQaResponse> getMerchantQaList(String merchantId) {
        return merchantQaRepository.findByMerchantId(merchantId).stream()
                .map(qa -> MerchantQaResponse.builder()
                        .question(qa.getQuestion())
                        .answer(qa.getAnswer())
                        .category(qa.getCategory())
                        .build())
                .collect(Collectors.toList());
    }

    // 상점 QA 챗봇 질문 (GPT fallback 포함)
    public MerchantChatResponse chatMerchant(MerchantChatRequest request) {
        // 1. 우선 저장된 QA에서 유사 질문 검색 (간단히 완전일치 또는 유사도 로직은 별도 구현 필요)
        MerchantQa matched = merchantQaRepository.findByMerchantIdAndQuestion(request.getMerchantId(), request.getQuestion());

        if (matched != null) {
            // 점수 1.0으로 확정 답변 제공
            return MerchantChatResponse.builder()
                    .matchedQuestion(matched.getQuestion())
                    .answer(matched.getAnswer())
                    .score(1.0)
                    .build();
        }

        // 2. 매칭 안되면 AI 서버 GPT fallback 호출 (간단 예시)
        String url = AI_SERVER_BASE_URL + "/chat/merchant";
        var aiRequest = new java.util.HashMap<String, Object>();
        aiRequest.put("merchant_id", request.getMerchantId());
        aiRequest.put("question", request.getQuestion());

        var aiResponse = restTemplate.postForObject(url, aiRequest, java.util.Map.class);

        String matchedQuestion = (String) aiResponse.get("matched_question");
        String answer = (String) aiResponse.get("answer");
        Double score = (Double) aiResponse.get("score");

        return MerchantChatResponse.builder()
                .matchedQuestion(matchedQuestion)
                .answer(answer)
                .score(score != null ? score : 0)
                .build();
    }
}
