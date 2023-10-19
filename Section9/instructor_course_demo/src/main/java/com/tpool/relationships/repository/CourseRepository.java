package com.tpool.relationships.repository;

import com.tpool.relationships.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT * FROM course where instructor_id = ?1",
    nativeQuery = true)
    List<Course> findCoursesByInstructorId(int instructor_id);
}
