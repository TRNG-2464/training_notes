package com.revature.expanded.controllers;

import com.revature.expanded.model.EmployeeLogin;
import com.revature.expanded.services.EmployeeLoginService;
import com.revature.expanded.util.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logins")
public class EmployeeLoginController {

    private final EmployeeLoginService employeeLoginService;

    public EmployeeLoginController(EmployeeLoginService employeeLoginService) {
        this.employeeLoginService = employeeLoginService;
    }

    // GET http://localhost:8080/logins
    @GetMapping
    public ResponseEntity<List<EmployeeLogin>> getAllLogins() {
        return ResponseEntity.ok(employeeLoginService.getAllLogins());
    }

    // GET http://localhost:8080/logins/username/joseph
    @GetMapping("/username/{username}")
    public ResponseEntity<EmployeeLogin> getLoginByUsername(@PathVariable String username) {
        return ResponseEntity.ok(employeeLoginService.getLoginByUsername(username));
    }

    // GET http://localhost:8080/logins/username-ci/JOSEPH
    @GetMapping("/username-ci/{username}")
    public ResponseEntity<EmployeeLogin> getLoginByUsernameIgnoreCase(@PathVariable String username) {
        return ResponseEntity.ok(employeeLoginService.getLoginByUsernameIgnoreCase(username));
    }

    // GET http://localhost:8080/logins/role/ROLE_EMPLOYEE
    @GetMapping("/role/{role}")
    public ResponseEntity<List<EmployeeLogin>> getActiveLoginsByRole(@PathVariable Role role) {
        return ResponseEntity.ok(employeeLoginService.getActiveLoginsByRole(role));
    }
}
