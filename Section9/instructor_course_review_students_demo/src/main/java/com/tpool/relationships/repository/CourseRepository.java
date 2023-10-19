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

    // get the course and all of the courses associated with them
    // load all the reviews because uses lazy loading by default
    @Query(value = "SELECT c FROM Course c LEFT JOIN FETCH c.reviews WHERE c.id=?1")
    Course findCourseLoadReviews(int courseId);

    // gets the course that has the following review id
    @Query(value = "SELECT c FROM Course c LEFT JOIN FETCH c.reviews r WHERE r.id = ?1")
    Course findCourseByReviewId(int reviewId);

    @Query(value = "SELECT c FROM Course c LEFT JOIN FETCH c.students WHERE c.id=?1")
    Course findCoursesLoadStudents(int courseId);
}
