package com.revature.basics.services;

import com.revature.basics.exceptions.EmployeeNotFoundException;
import com.revature.basics.model.Employee;
import com.revature.basics.model.EmployeeLogin;
import com.revature.basics.repository.EmployeeLoginRepository;
import com.revature.basics.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Our Service Layer is responsible for our business logic
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeLoginRepository employeeLoginRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeLoginRepository employeeLoginRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeLoginRepository = employeeLoginRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public List<Employee> searchByTitle(String title) {
        return employeeRepository.findByEmpTitleIgnoreCase(title);
    }

    // Note: This is a simple CRUD method to create an Employee Record
    // However, we should really remove this and replace its use with
    // the '@Transactional' method below...
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /* without @Transactional here, if the employeeLoginRepository.save() fails
     * AFTER the employeeRepository.save() already succeeded, the database is
     * left in a broken, half-finished state. An employee exists with no way
     * to log in, and nothing rolls back to prevent that from happening...
     */
    @Transactional
    public Employee onboardNewEmployee(Employee newEmployee, EmployeeLogin newLogin) {
        Employee savedEmployee = employeeRepository.save(newEmployee);

        newLogin.setEmpId(savedEmployee.getEmpId());
        employeeLoginRepository.save(newLogin);
        // if saving the login throws an exception (e.g. a duplicate username is used)
        // the entire transaction will be rolled back

        return savedEmployee;
    }

    public Employee replaceEmployee(Integer id, Employee employee) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employee.setEmpId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }
}
