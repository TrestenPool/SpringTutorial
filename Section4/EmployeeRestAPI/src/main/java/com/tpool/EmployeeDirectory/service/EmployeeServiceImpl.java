package com.tpool.EmployeeDirectory.service;

import com.tpool.EmployeeDirectory.dao.EmployeeDao;
import com.tpool.EmployeeDirectory.dao.EmployeeDaoImpl;
import com.tpool.EmployeeDirectory.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // all methods will be wrapped in a transaction
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDaoImpl employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDaoImpl employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee deleteById(int id) {
        return employeeDao.deleteById(id);
    }
}
