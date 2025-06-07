package com.Locathon.Course.respository;

import com.Locathon.Course.model.CoursePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursePlaceRepository extends JpaRepository<CoursePlace, Long> {
    List<CoursePlace> findByCourseIdOrderByOrderIndexAsc(Long courseId);
}