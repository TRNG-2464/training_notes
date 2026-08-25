package com.revature.basics.services;

import com.revature.basics.exceptions.LoginNotFoundException;
import com.revature.basics.model.EmployeeLogin;
import com.revature.basics.repository.EmployeeLoginRepository;
import com.revature.basics.util.Role;
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
}
