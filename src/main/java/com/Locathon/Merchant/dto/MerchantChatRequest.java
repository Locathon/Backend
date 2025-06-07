package com.Locathon.Merchant.dto;

import lombok.*;

// 상점 QA 챗봇 질문 요청 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantChatRequest {
    private String merchantId;
    private String question;
}
