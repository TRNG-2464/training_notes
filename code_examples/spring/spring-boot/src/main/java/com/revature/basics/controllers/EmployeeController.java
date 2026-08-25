package com.revature.basics.controllers;

import com.revature.basics.model.Employee;
import com.revature.basics.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Combination of @Controller and @ResponseBody (applied to all methods in this class)
@RequestMapping("/employees")   // Base path for all other Mapping methods within this Controller
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
    // Showcases @PathVariable
    // Path variables define a specific value in the URL
    // i.e. {id} which is extracted and used in the method
    // body. Typically used to find a specific resource
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(employeeService.searchByTitle(title));
    }

    // POST http://localhost:8080/employees
    // Showcases @RequestBody
    // Allows this method to read data within the request body
    // (Typically as JSON), and parses that JSON into a defined
    // Entity model.
    // Body (raw JSON): {"empId": 1000, "empName": "Jane Doe", "empTitle": "Engineer", "empSalary": 85000}
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT http://localhost:8080/employees/1000
    // Showcases a combination of @PathVariable and @RequestBody for updates
    // This allows you to pass the details of the Employee in the body
    // of the HTML request, and use the path variable to determine
    // which employee's details should be updated
    @PutMapping("/{id}")
    public ResponseEntity<Employee> replaceEmployee(@PathVariable Integer id,
                                                    @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.replaceEmployee(id, employee));
    }


    // DELETE http://localhost:8080/employees/1000
    // Note that Delete doesn't provide any significant data to return
    // so the Response Entity contains no Content (204)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
