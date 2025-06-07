package com.Locathon.Course.service;

import com.Locathon.Course.dto.CourseDto;
import com.Locathon.Course.dto.CourseListDto;
import com.Locathon.Course.dto.CoursePlaceDto;
import com.Locathon.model.Course;
import com.Locathon.model.CoursePlace;
import com.Locathon.Course.respository.CoursePlaceRepository;
import com.Locathon.Course.respository.CourseRepository;
import com.Locathon.model.Member;
import com.Locathon.model.Place;
import com.Locathon.Place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
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
    public Course createCourse(CourseDto courseDto, Member memberDetails) {
        List<CoursePlaceDto> placeDtos = courseDto.getCoursePlaces();

        if (placeDtos == null || placeDtos.size() < 2) {
            throw new IllegalArgumentException("2개 이상 장소를 선택해주세요.");
        }

        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setCreatedBy(memberDetails);
        courseRepository.save(course);

        List<CoursePlace> coursePlaces = new ArrayList<>();
        for (CoursePlaceDto dto : placeDtos) {
            Place place = placeRepository.findById(dto.getPlaceId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid place ID: " + dto.getPlaceId()));

            CoursePlace cp = new CoursePlace();
            cp.setCourse(course);
            cp.setPlace(place);
            cp.setOrderIndex(dto.getOrderIndex());

            coursePlaces.add(cp);
        }

        course.setCoursePlaceList(coursePlaces);
        coursePlaceRepository.saveAll(coursePlaces);

        return course;
    }

    //코스 조회
    @Transactional(readOnly = true)
    public CourseListDto getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("코스를 찾을 수 없습니다."));

        List<CoursePlaceDto> placeDtos = course.getCoursePlaceList().stream()
                .map(cp -> new CoursePlaceDto(cp.getPlace().getId(), cp.getOrderIndex()))
                .toList();

        CourseListDto dto = new CourseListDto();
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setCreatedById(course.getCreatedBy().getId());
        dto.setCoursePlaces(placeDtos);

        return dto;
    }
}