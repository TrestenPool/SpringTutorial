package com.tpool.relationships.repository;

import com.tpool.relationships.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Query("SELECT i FROM Instructor i LEFT JOIN FETCH i.courses LEFT JOIN FETCH i.instructorDetail WHERE i.id = ?1")
   Instructor findInstructorByIdJoinFetch(int id);
}
