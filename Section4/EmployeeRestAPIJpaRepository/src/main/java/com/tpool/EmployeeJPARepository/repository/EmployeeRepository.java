package com.tpool.EmployeeJPARepository.repository;

import com.tpool.EmployeeJPARepository.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
