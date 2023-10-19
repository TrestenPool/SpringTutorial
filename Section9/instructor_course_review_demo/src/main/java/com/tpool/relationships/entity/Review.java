package com.tpool.relationships.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "review")
public class Review {
    // fields
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 256)
    private String comment;

    // constructor
    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    // tostring
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }

    // getter and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}