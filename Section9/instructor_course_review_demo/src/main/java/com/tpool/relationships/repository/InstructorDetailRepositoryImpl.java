package com.tpool.relationships.repository;

import com.tpool.relationships.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InstructorDetailRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    // method
    @SuppressWarnings("unused")
    @Transactional
    public void RemoveDetailKeepInstructor(int id) {
        // finds the instructor detail by the id
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        // break the bi-directional link
        instructorDetail.getInstructor().setInstructorDetailId(null);

        // remove the instructor detail
        entityManager.remove(instructorDetail);
    }
}
