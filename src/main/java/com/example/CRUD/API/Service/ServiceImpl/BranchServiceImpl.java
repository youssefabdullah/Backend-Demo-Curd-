package com.example.CRUD.API.Service.ServiceImpl;

import com.example.CRUD.API.Repository.BranchRepository;
import com.example.CRUD.API.Service.BranchService;
import com.example.CRUD.API.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository repository;

    public Branch findById(Long id) {
        return repository.findById(id);
    }

    public List<Branch> findAll() {
        return repository.findAll();
    }

    public Branch save(Branch branch) {
        return repository.save(branch);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
