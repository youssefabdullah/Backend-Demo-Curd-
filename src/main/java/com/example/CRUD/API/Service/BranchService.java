package com.example.CRUD.API.Service;

import com.example.CRUD.API.model.Branch;

import java.util.List;

public interface BranchService {
    public Branch findById(Long id);
    public List<Branch> findAll();
    public Branch save(Branch branch);
    public void deleteById(Long id);
}
