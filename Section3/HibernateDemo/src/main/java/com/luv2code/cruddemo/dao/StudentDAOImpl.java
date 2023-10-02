package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student); // saves the student in the db
    }

    // find method
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "FROM Student", Student.class);
        return typedQuery.getResultList();
    }


    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> typedQuery = entityManager.createQuery(
                "FROM Student WHERE lastName = :variable",
                Student.class
        );
        typedQuery.setParameter("variable", lastName);
        return typedQuery.getResultList();
    }


    @Override
    @Transactional
    public void update(Student student) {
        // this updates the student
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public Student delete(Integer id) {
        // gets the student from the db
        Student student = this.findById(id);

        // student not found
        if(student == null){
            return null;
        }
        // delete from the db
        entityManager.remove(student);
        // return the student
        return student;
    }


    @Override
    @Transactional
    public int deleteAll() {
        List<Student> students = this.findAll();
        int num_students = students.size();
        for(var student : students){
            System.out.print("Deleting student: ");
            System.out.println(student);
            this.delete(student.getId());
        }
        return num_students;
    }

}
