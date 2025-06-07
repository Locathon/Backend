package com.Locathon.Course.dto;

import com.Locathon.model.Place;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CourseListDto {
    private String name;
    private String description;
    private Long createdById;
    private List<Place> coursePlaces;  // 순서 포함한 장소 목록

}