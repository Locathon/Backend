package com.Locathon.Merchant.dto;

import lombok.*;

// 상점 QA 응답 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantQaResponse {
    private String question;
    private String answer;
    private String category;
}
