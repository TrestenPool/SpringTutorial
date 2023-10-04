package com.tpool.EmployeeDirectory.service;

import com.tpool.EmployeeDirectory.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee deleteById(int id);
}
