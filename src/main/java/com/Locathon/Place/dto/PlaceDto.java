package com.Locathon.Place.dto;

import com.Locathon.model.Member;
import lombok.*;

/* 프론트로부터 받아올 Place DTO*/
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    private String name;
    private String title;
    private String content;
    private Long latitude;
    private Long longitude;

    private Member createdBy;
}