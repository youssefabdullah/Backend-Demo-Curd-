package com.example.CRUD.API.Service;

import com.example.CRUD.API.model.Branch;
import com.example.CRUD.API.model.Employee;

import java.util.List;

public interface EmployeeService {
     Employee findById(Long id);
     List<Employee> findAll();
     Employee save(Employee employee);
     void deleteById(Long id);
     Employee update(Long id,Employee employee);
}
