package com.tpool.EmployeeDirectory.dao;

import com.tpool.EmployeeDirectory.entity.Employee;

import java.util.List;

public interface EmployeeDao {
   List<Employee> findAll();
   Employee findById(int id);
   Employee save(Employee employee);
   Employee deleteById(int id);
}
