package com.example.CRUD.API.Controller;

import com.example.CRUD.API.Service.EmployeeService;
import com.example.CRUD.API.model.Branch;
import com.example.CRUD.API.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/")
    public List<Employee> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        Long newId = (long) id;
        return service.findById(newId);
    }

    @PostMapping("/")
    public Employee save(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        Long newId = (long) id;
        service.deleteById(newId);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable int id, @RequestBody Employee employee) {
        Long newId = (long) id;
        return service.update(newId, employee);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
