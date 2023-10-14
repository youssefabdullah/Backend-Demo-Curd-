package com.example.CRUD.API.Controller;

import com.example.CRUD.API.Service.BranchService;
import com.example.CRUD.API.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/branchs")
@CrossOrigin("http://localhost:3000")
public class BranchController {
    @Autowired
    private BranchService service;

    @GetMapping("/")
    public List<Branch> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Branch findById(@PathVariable int id) {
        Long newId = (long) id;
        return service.findById(newId);
    }

    @PostMapping("/")
    public Branch save(@RequestBody Branch branch) {
        return service.save(branch);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        Long newId = (long) id;
        service.deleteById(newId);
    }

}
