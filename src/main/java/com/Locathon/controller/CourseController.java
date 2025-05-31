package com.Locathon.controller;
import com.Locathon.dto.CourseDto;
import com.Locathon.dto.CourseListDto;
import com.Locathon.model.Course;
import com.Locathon.model.Member;
import com.Locathon.repository.MemberRepository;
import com.Locathon.service.CourseService;
import com.Locathon.service.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
}