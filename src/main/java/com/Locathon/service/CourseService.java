package com.Locathon.service;

import com.Locathon.dto.CourseDto;
import com.Locathon.dto.CoursePlaceDto;
import com.Locathon.model.*;
import com.Locathon.repository.CoursePlaceRepository;
import com.Locathon.repository.CourseRepository;
import com.Locathon.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final PlaceRepository placeRepository;
    private final CoursePlaceRepository coursePlaceRepository;

    //코스 생성
    @Transactional
    public Course createCourse(CourseDto courseDto, @AuthenticationPrincipal Member userDetails) {
        List<CoursePlaceDto> placeDtos = courseDto.getCoursePlaces();

        if (placeDtos == null || placeDtos.size() < 2) {
            throw new IllegalArgumentException("2개 이상 장소를 선택해주세요.");
        }

        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setCreatedBy(userDetails);
        courseRepository.save(course);

        List<CoursePlace> coursePlaces = new ArrayList<>();
        for (CoursePlaceDto dto : placeDtos) {
            Place place = placeRepository.findById(dto.getPlaceId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid place ID: " + dto.getPlaceId()));

            CoursePlace cp = new CoursePlace();
            cp.setCourse(course);
            cp.setPlace(place);
            cp.setOrderIndex(dto.getOrderIndex());

            // TODO: 거리 및 이동 시간 계산 로직 추가 가능 (Haversine, Kakao API 등)
            coursePlaces.add(cp);
        }

        course.setCoursePlaceList(coursePlaces);
        coursePlaceRepository.saveAll(coursePlaces);

        return course;
    }

    //코스 조회
    @Transactional(readOnly = true)
    public CourseDto getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("코스를 찾을 수 없습니다."));

        List<CoursePlaceDto> placeDtos = course.getCoursePlaceList().stream()
                .map(cp -> new CoursePlaceDto(cp.getPlace().getId(), cp.getOrderIndex()))
                .toList();

        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setCreatedById(course.getCreatedBy().getId());
        dto.setCoursePlaces(placeDtos);

        return dto;
    }
}