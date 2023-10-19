package com.tpool.relationships.service;

import com.tpool.relationships.entity.Course;
import com.tpool.relationships.entity.Instructor;
import com.tpool.relationships.entity.InstructorDetail;
import com.tpool.relationships.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {
    // fields
    private CourseRepository courseRepository;
    private InstructorRepository instructorRepository;
    private InstructorDetailRepository instructorDetailRepository;
    private ReviewRepository reviewRepository;
    private StudentRepository studentRepository;

    @Autowired
    public InstructorServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository, InstructorDetailRepository instructorDetailRepository, ReviewRepository reviewRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.instructorDetailRepository = instructorDetailRepository;
        this.reviewRepository = reviewRepository;
        this.studentRepository = studentRepository;
    }



    // getters and setters

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void setInstructorRepository(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public void setInstructorDetailRepository(InstructorDetailRepository instructorDetailRepository) {
        this.instructorDetailRepository = instructorDetailRepository;
    }

    public ReviewRepository getReviewRepository() {
        return reviewRepository;
    }

    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    public InstructorRepository getInstructorRepository() {
        return instructorRepository;
    }

    public InstructorDetailRepository getInstructorDetailRepository() {
        return instructorDetailRepository;
    }
}
