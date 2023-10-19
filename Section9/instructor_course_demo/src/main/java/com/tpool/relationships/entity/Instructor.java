package com.tpool.relationships.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "instructor")
public class Instructor {
    // field
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_detail_id") // join the tables by this column name specified in the quotes
    private InstructorDetail instructorDetail;

    // one instructor can have multiple courses
    // mapped by the instructor variable in the course class
    @OneToMany(mappedBy = "instructor",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Course> courses;

    // bi-directional link
    public void addCourse(Course course){

        // intialize if necessary
        if(courses == null){
            courses = new ArrayList<>();
        }

        // set the instructor on the course
        course.setInstructor(this);

        // add the course to this instructor course list
        courses.add(course);
    }
    
    public void removeCourse(int courseId){
        Iterator<Course> iterator = this.courses.iterator();
        while(iterator.hasNext()) {
            Course tempCourse = iterator.next();
            if(tempCourse.getId() == courseId) {
                iterator.remove();
            }
        }
    }

    // constructors
    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // tostring
    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetailId() {
        return instructorDetail;
    }

    public void setInstructorDetailId(InstructorDetail instructorDetailId) {
        this.instructorDetail = instructorDetailId;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
