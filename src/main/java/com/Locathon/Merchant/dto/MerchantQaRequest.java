package com.Locathon.Merchant.dto;

import lombok.*;

// 상점 QA 추가/수정 요청 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantQaRequest {
    private String question;
    private String answer;
    private String category;
}
