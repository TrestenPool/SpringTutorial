package com.tpool.EmployeeDirectory.rest;

import com.tpool.EmployeeDirectory.dao.EmployeeDao;
import com.tpool.EmployeeDirectory.entity.Employee;
import com.tpool.EmployeeDirectory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // get all employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // get a single employee by id
    @GetMapping("/employees/{employee_id}")
    public Employee getEmployee(@PathVariable int employee_id){
        Employee employee = employeeService.findById(employee_id);
        if(employee == null){
           throw new RuntimeException("Employee id not found - " + employee_id);
        }
        return employee;
    }

    // insert a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // updates an employee or adds it if not in the db
    @PutMapping("/employees/{employee_id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable int employee_id){
        // the id does not exist
        if(employeeService.findById(employee_id) == null){
            throw new RuntimeException("No employee with id: " +employee_id + " was found");
        }
        employee.setId(employee_id);
        employeeService.save(employee);
        return employee;
    }

    // delete an employee by id
    @DeleteMapping("/employees/{employee_id}")
    public Employee deleteEmployee(@PathVariable int employee_id){
        if(employeeService.findById(employee_id) == null){
            throw new RuntimeException("Employee with id: " + employee_id + " was not found...");
        }
        return employeeService.deleteById(employee_id);
    }

}
