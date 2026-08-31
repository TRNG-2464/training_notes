package com.revature.expanded.controllers;

import com.revature.expanded.model.Employee;
import com.revature.expanded.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

// No Spring context loaded here — just Mockito, calling the controller's
// methods directly as plain Java method calls.
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTestBasic {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    void getEmployeeById_delegatesToServiceAndReturnsOk() {
        Employee employee = new Employee(1000, "Jane Doe", "Engineer", new BigDecimal("85000.00"));
        when(employeeService.getEmployeeById(1000)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(1000);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(employee);
    }

    @Test
    void createEmployee_delegatesToServiceAndReturnsCreated() {
        Employee input = new Employee(null, "New Hire", "Associate", new BigDecimal("60000.00"));
        Employee saved = new Employee(1001, "New Hire", "Associate", new BigDecimal("60000.00"));
        when(employeeService.createEmployee(input)).thenReturn(saved);

        ResponseEntity<Employee> response = employeeController.createEmployee(input);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(saved);
    }
}