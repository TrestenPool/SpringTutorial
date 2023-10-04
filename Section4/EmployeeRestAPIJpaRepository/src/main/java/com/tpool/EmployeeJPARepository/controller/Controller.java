package com.tpool.EmployeeJPARepository.controller;

import com.tpool.EmployeeJPARepository.entity.Employee;
import com.tpool.EmployeeJPARepository.service.EmployeeService;
import com.tpool.EmployeeJPARepository.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private EmployeeService employeeService;

    @Autowired
    public Controller(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    // get single employee
    @GetMapping("/employees/{employee_id}")
    public Employee getEmployee(@PathVariable int employee_id){
        Employee employee = employeeService.findById(employee_id);
        if(employee == null){
            throw new RuntimeException("No employee with id: " +employee_id +" was found");
        }
        return employee;
    }

    // add new employee
    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee employee){
       return employeeService.save(employee);
    }

    // update single employee
    @PutMapping("/employees/{employee_id}")
    public Employee newEmployee(@RequestBody Employee employee, @PathVariable int employee_id){
        if(employeeService.findById(employee_id) == null){
            throw new RuntimeException("Employee with id: " +employee_id +" was not found...");
        }
        employee.setId(employee_id);
        return employeeService.save(employee);
    }

    // delete single employee
    @DeleteMapping("employees/{employee_id}")
    public Employee deleteEmployee(@PathVariable int employee_id){
        Employee employee = employeeService.removeById(employee_id);
        if(employee == null){
            throw new RuntimeException("No employee with id: " +employee_id +" was found...");
        }
        return employee;
    }

}
