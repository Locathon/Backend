package com.Locathon.Course.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CourseDto {
    private String name;
    private String description;
    private List<CoursePlaceDto> coursePlaces;  // 순서 포함한 장소 목록
}
