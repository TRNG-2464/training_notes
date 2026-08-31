package com.revature.expanded.services;

import com.revature.expanded.exceptions.EmployeeNotFoundException;
import com.revature.expanded.logging.LogExecution;
import com.revature.expanded.model.Employee;
import com.revature.expanded.model.EmployeeLogin;
import com.revature.expanded.repository.EmployeeLoginRepository;
import com.revature.expanded.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeLoginRepository employeeLoginRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeLoginRepository employeeLoginRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeLoginRepository = employeeLoginRepository;
    }

    /*
     * Now, when this method is executed, we will log the execution
     */
    @LogExecution
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @LogExecution
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @LogExecution
    public List<Employee> searchByTitle(String title) {
        return employeeRepository.findByEmpTitleIgnoreCase(title);
    }

    @LogExecution
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    @LogExecution
    public Employee onboardNewEmployee(Employee newEmployee, EmployeeLogin newLogin) {
        Employee savedEmployee = employeeRepository.save(newEmployee);

        newLogin.setEmpId(savedEmployee.getEmpId());
        employeeLoginRepository.save(newLogin);

        return savedEmployee;
    }

    @LogExecution
    public Employee replaceEmployee(Integer id, Employee employee) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employee.setEmpId(id);
        return employeeRepository.save(employee);
    }

    @LogExecution
    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }
}
