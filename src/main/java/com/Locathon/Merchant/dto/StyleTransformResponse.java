package com.Locathon.Merchant.dto;

import lombok.*;

// 스타일 변환 요청 DTO


// 스타일 변환 응답 DTO
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class StyleTransformResponse {
    private String original;
    private String transformed;
}

