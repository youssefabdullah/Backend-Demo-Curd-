package com.example.CRUD.API.Repository.RepositoryImpl;

import com.example.CRUD.API.Repository.EmployeeRepository;
import com.example.CRUD.API.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            entityManager.persist(employee);
        } else {
            entityManager.merge(employee);
        }
        return employee;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Employee employee = findById(id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }

    @Override
    @Transactional
    public Employee update(Long id, Employee updatedProduct) {
        Employee existingEmployee = entityManager.find(Employee.class, id);

        if (existingEmployee == null) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }


        existingEmployee.setName(updatedProduct.getName());
        existingEmployee.setBranch(updatedProduct.getBranch());

        entityManager.merge(existingEmployee);

        return existingEmployee;
    }
}
