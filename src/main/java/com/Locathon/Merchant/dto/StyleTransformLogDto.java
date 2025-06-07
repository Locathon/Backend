package com.Locathon.Merchant.dto;

import lombok.*;

import java.time.LocalDateTime;

// 스타일 변환 로그 조회 응답 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleTransformLogDto {
    private LocalDateTime timestamp;
    private String original;
    private String transformed;
}
