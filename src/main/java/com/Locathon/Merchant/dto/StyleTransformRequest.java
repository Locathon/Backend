package com.Locathon.Merchant.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleTransformRequest {
    private String originalText;
    private String tone;  // optional
}