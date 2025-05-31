package com.Locathon.controller;
import com.Locathon.dto.CourseDto;
import com.Locathon.model.Course;
import com.Locathon.model.Member;
import com.Locathon.repository.MemberRepository;
import com.Locathon.service.CourseService;
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
    private final MemberRepository memberRepository;


    //코스 생성
    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto, Member member) {
        Course course = courseService.createCourse(courseDto, member);
        CourseDto result = courseService.getCourse(course.getId());
        return ResponseEntity.ok(result);
    }

     //코스 조회
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id) {
        CourseDto dto = courseService.getCourse(id);
        return ResponseEntity.ok(dto);
    }
}