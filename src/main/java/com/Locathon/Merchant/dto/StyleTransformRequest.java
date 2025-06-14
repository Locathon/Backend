package com.Locathon.Merchant.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleTransformRequest {
    private String originalText;
    private String tone; // optional, legacy style
    private String storeName; // optional, for enhanced control
    private String sns; // optional, for SNS-style tone mapping
}