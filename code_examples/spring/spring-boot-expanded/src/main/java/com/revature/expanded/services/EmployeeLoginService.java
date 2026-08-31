package com.revature.expanded.services;

import com.revature.expanded.exceptions.LoginNotFoundException;
import com.revature.expanded.model.EmployeeLogin;
import com.revature.expanded.repository.EmployeeLoginRepository;
import com.revature.expanded.util.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeLoginService {

    private final EmployeeLoginRepository employeeLoginRepository;

    public EmployeeLoginService(EmployeeLoginRepository employeeLoginRepository) {
        this.employeeLoginRepository = employeeLoginRepository;
    }

    public List<EmployeeLogin> getAllLogins() {
        return employeeLoginRepository.findAll();
    }

    public EmployeeLogin getLoginByUsername(String username) {
        return employeeLoginRepository.findByUsername(username)
                .orElseThrow(() -> new LoginNotFoundException(username));
    }

    public EmployeeLogin getLoginByUsernameIgnoreCase(String username) {
        return employeeLoginRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new LoginNotFoundException(username));
    }

    public List<EmployeeLogin> getActiveLoginsByRole(Role role) {
        return employeeLoginRepository.findByRoleAndEnabledTrue(role);
    }

    // This method is used as part of a Spring Security check
    // It checks if the username provide matches the requested EmpId
    public boolean isOwnRecord(String username, Integer requestedEmpId) {
        return employeeLoginRepository.findByUsername(username)
                .map(login -> login.getEmpId().equals(requestedEmpId))
                .orElse(false);
    }
}
