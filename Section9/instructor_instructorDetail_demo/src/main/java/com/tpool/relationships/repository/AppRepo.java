package com.tpool.relationships.repository;

import com.tpool.relationships.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AppRepo extends JpaRepository<Instructor, Integer> {
}
