package com.Locathon.Course.controller;
import com.Locathon.Course.dto.CourseDto;
import com.Locathon.Course.dto.CourseListDto;
import com.Locathon.Course.service.CourseService;
import com.Locathon.model.Course;
import com.Locathon.Member.service.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    //코스 생성
    @PostMapping
    public ResponseEntity<CourseListDto> createCourse(@RequestBody CourseDto courseDto, @AuthenticationPrincipal MemberDetails memberDetails) {
        Course course = courseService.createCourse(courseDto, memberDetails.getMember());
        CourseListDto result = courseService.getCourse(course.getId());
        return ResponseEntity.ok(result);
    }

     //코스 조회
    @GetMapping("/{id}")
    public ResponseEntity<CourseListDto> getCourse(@PathVariable Long id) {
        CourseListDto dto = courseService.getCourse(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public List<CourseListDto> getCourseList() {
        return courseService.getCourseList();
    }
}