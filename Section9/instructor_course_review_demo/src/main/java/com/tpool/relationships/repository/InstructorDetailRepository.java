package com.tpool.relationships.repository;

import com.tpool.relationships.entity.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer> {
    // custom method
    public void RemoveDetailKeepInstructor(int id);
}
