package com.tpool.relationships.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name="course")
public class Course {
    // fields
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 128)
    private String title;

    // multiple courses can map to one instructor
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    // constructor
    public Course(String title, Instructor instructor) {
        this.title = title;
        this.instructor = instructor;
    }

    public Course() {
    }

    // custom methods
    public void addReview(Review review) {
        if(this.reviews == null) {
            this.reviews = new ArrayList<>();
        }

        this.reviews.add(review);
    }
    public void removeReview(int reviewId) {
        // no reviews in here, just return
        if(this.reviews == null) {
            return;
        }

        Iterator<Review> iterator = reviews.iterator();
        while(iterator.hasNext()) {
            Review currReview = iterator.next();
            if(currReview.getId().equals(reviewId)) {
                System.out.println("Removing the review with comment: " +currReview.getComment());
                iterator.remove();
            }
        }
        System.out.println("done");
    }

    // getters and setters
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // tostring
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
