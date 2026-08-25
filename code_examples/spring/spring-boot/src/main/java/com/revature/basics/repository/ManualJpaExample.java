package com.revature.basics.repository;

import com.revature.basics.model.Employee;
import jakarta.persistence.EntityManager;

/*
 * This example shows (roughly) what Spring Data JPA
 * is doing on your behalf. Using the plain JPA EntityManager
 * is not a recommended approach in a Spring project
 */
public class ManualJpaExample {

    private EntityManager entityManager;

    public Employee findEmployeeById(Integer id) {
        return entityManager.find(Employee.class, id);
    }

    public Employee saveEmployee(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }
}
