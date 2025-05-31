package com.Locathon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    private String name;
    private String description;
    private Double latitude;
    private Double longitude;
    private String address;
}