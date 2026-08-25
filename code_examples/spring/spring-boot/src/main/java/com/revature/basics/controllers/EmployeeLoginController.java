package com.revature.basics.controllers;

import com.revature.basics.model.EmployeeLogin;
import com.revature.basics.services.EmployeeLoginService;
import com.revature.basics.util.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
