package com.revature.basics.controllers;

import com.revature.basics.model.Employee;
import com.revature.basics.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/improved/employees")
public class EmployeeControllerImproved {
    private final EmployeeRepository employeeRepository;

    public EmployeeControllerImproved(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getEmployeeData() {
        return employeeRepository.findAll();
    }

    // GET http://localhost:8080/search?title=Value | http://localhost:8080/search
    // Showcases an optional @RequestParam
    // Request Parameters can be used to define a value that
    // is present after a '?' in a GET request (recall HTML forms)
    // In this example, it showcases how we can provide a default
    // return value is a request parameter is omitted
    @GetMapping("/search")
    public List<Employee> searchByTitle(@RequestParam(required = false) String title) {
        if (title == null) return employeeRepository.findAll();

        return employeeRepository.findAll()
                .stream()
                .filter(emp -> emp.getEmpTitle().equalsIgnoreCase(title))
                .toList();
    }

    // GET http://localhost:8080/paged?limit=10 | http://localhost:8080/paged
    // Showcases a default value with @RequestParam
    // here, spring will supply the value "5" automatically if none is
    // provided in the URL of the Get request
    @GetMapping("/paged")
    public List<Employee> getPagedEmployees(@RequestParam(defaultValue="5") int limit) {
        return employeeRepository.findAll()
                .stream()
                .limit(limit)
                .toList();
    }

    /*
     * Here, the Builder Pattern is used to create a Response Entity
     * with an OK status if the record is found in our database
     * otherwise, we return a notFound() response (404)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)    // if found, return 200 OK with Employee record data
                .orElse(ResponseEntity.notFound().build()); // otherwise return 404 if not found
    }

    // POST mapping which returns 201 (Created) instead of a default 200
    // Additionally, we wrap the saved response from our repository in the
    // reponse body
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT - this returns 404 if the Employee being updated doesn't exist
    // In the 'basic' example (EmployeeController) - the logic we had
    // would actually silently add a new record, which leads to a misleading
    // 200 OK response
    @PutMapping("/{id}")
    public ResponseEntity<Employee> replaceEmployee(@PathVariable Integer id,
                                                    @RequestBody Employee employee) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // return 404 if the emp with ID doesn't exist
        }

        employee.setEmpId(id); // use the ID passed in URL
        Employee updated = employeeRepository.save(employee);
        return ResponseEntity.ok(updated);  // return 200 with the updated Employee as the body
    }

    // DELETE - This returns a 204 (No Content) on success since there
    // is no meaningful data left to send back after removing the record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // if the Employee doesn't exist, return 404
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // returns 204 - empty body
    }



}
