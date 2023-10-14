package com.example.CRUD.API.Repository.RepositoryImpl;

import com.example.CRUD.API.Repository.BranchRepository;
import com.example.CRUD.API.model.Branch;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BranchRepositoryImpl implements BranchRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Branch findById(Long id) {
        return entityManager.find(Branch.class, id);
    }

    @Override
    public List<Branch> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Branch> query = criteriaBuilder.createQuery(Branch.class);
        Root<Branch> root = query.from(Branch.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public Branch save(Branch branch) {
        if (branch.getId() == null) {
            entityManager.persist(branch);
        } else {
            entityManager.merge(branch);
        }
        return branch;
    }

    @Override
    public void deleteById(Long id) {
        Branch branch = findById(id);
        if (branch != null) {
            entityManager.remove(branch);
        }
    }
}
