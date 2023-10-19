package com.tpool.relationships.service;

import com.tpool.relationships.entity.Course;
import com.tpool.relationships.entity.Instructor;
import com.tpool.relationships.entity.InstructorDetail;
import com.tpool.relationships.repository.CourseRepository;
import com.tpool.relationships.repository.InstructorDetailRepository;
import com.tpool.relationships.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {
    // fields
    private CourseRepository courseRepository;
    private InstructorRepository instructorRepository;
    private InstructorDetailRepository instructorDetailRepository;

    // controller injection for the fields
    @Autowired
    public InstructorServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository, InstructorDetailRepository instructorDetailRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.instructorDetailRepository = instructorDetailRepository;
    }


    // getters
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
