package com.Locathon.Merchant.dto;

import lombok.*;

// 상점 QA 챗봇 응답 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantChatResponse {
    private String matchedQuestion;
    private String answer;
    private double score;
}
