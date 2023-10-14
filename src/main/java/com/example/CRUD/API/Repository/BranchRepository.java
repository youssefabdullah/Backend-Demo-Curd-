package com.example.CRUD.API.Repository;
import com.example.CRUD.API.model.Branch;

import java.util.List;

public interface BranchRepository {
    Branch findById(Long id);
    List<Branch> findAll();
    Branch save(Branch branch);
    void deleteById(Long id);
}
