package com.Locathon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CoursePlaceDto {
    private Long placeId;     // 참조된 장소 ID
    private int orderIndex;   // 순서
}