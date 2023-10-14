package com.example.CRUD.API.Repository;

import com.example.CRUD.API.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee findById(Long id);
    List<Employee> findAll();
    Employee save(Employee employee);
    void deleteById(Long id);
    Employee update(Long productId, Employee updatedProduct);
}
