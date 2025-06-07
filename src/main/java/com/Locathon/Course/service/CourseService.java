package com.Locathon.Course.service;

import com.Locathon.Course.dto.CourseDto;
import com.Locathon.Course.dto.CourseListDto;
import com.Locathon.model.Course;
import com.Locathon.Course.respository.CourseRepository;
import com.Locathon.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    //코스 생성
    @Transactional
    public Course createCourse(CourseDto courseDto, Member memberDetails) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setContent(courseDto.getContent());
        course.setCreatedBy(memberDetails);

        course.setCoursePlaces(courseDto.getCoursePlaces());
        return courseRepository.save(course);
    }

    //코스 조회
    @Transactional(readOnly = true)
    public CourseListDto getCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("코스를 찾을 수 없습니다."));

        // CourseListDto 생성 및 값 세팅
        CourseListDto dto = new CourseListDto();
        dto.setName(course.getName());
        dto.setDescription(course.getContent()); // Course에서 content가 description 역할
        dto.setCreatedById(course.getCreatedBy().getId());
        dto.setCoursePlaces(course.getCoursePlaces());  // Place 엔티티 리스트 그대로 넣음

        return dto;
    }

    @Transactional(readOnly = true)
    public List<CourseListDto> getCourseList() {
        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .map(course -> {
                    CourseListDto dto = new CourseListDto();
                    dto.setName(course.getName());
                    dto.setDescription(course.getContent());
                    dto.setCreatedById(course.getCreatedBy().getId());
                    dto.setCoursePlaces(course.getCoursePlaces());
                    return dto;
                })
                .toList();
    }


}