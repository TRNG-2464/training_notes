package com.revature.expanded.controllers;

import com.revature.expanded.model.Employee;
import com.revature.expanded.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET http://localhost:8080/employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // GET http://localhost:8080/employees/1000
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // GET http://localhost:8080/employees/search?title="value"
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchByTitle(@RequestParam(required = false) String title) {
        return ResponseEntity.ok(employeeService.searchByTitle(title));
    }

    // POST http://localhost:8080/employees
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT http://localhost:8080/employees/1000
    @PutMapping("/{id}")
    public ResponseEntity<Employee> replaceEmployee(@PathVariable Integer id,
                                                    @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.replaceEmployee(id, employee));
    }

    // DELETE http://localhost:8080/employees/1000
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * TODO:Create an 'onboard' endpoint that takes an OnboardEmployeeRequest DTO
     *  as the @RequestBody - this should then call the appropriate, transactional
     *  service method to save the new Employee and it's associates login details
     *  to the database. - This should coincide with a removal of the basic Employee
     *  creation using the 'POST /employees' endpoint
     */
}
