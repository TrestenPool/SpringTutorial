package com.tpool.EmployeeJPARepository.service;

import com.tpool.EmployeeJPARepository.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee save(Employee employee);
    List<Employee> findAll();
    Employee findById(int id);
    Employee removeById(int id);
}
