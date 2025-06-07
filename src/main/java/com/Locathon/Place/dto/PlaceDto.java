package com.Locathon.Place.dto;

import com.Locathon.model.Member;
import com.Locathon.model.PlaceImage;
import lombok.*;

import java.util.List;

/* 프론트로부터 받아올 Place DTO*/
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    private String name;
    private String title;
    private String content;
    private Double latitude;
    private Double longitude;

    private List<String> imageUrls;

}