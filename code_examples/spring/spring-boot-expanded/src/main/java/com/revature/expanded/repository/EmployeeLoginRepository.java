package com.revature.expanded.repository;

import com.revature.expanded.model.EmployeeLogin;
import com.revature.expanded.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeLoginRepository extends JpaRepository<EmployeeLogin, Integer> {
    Optional<EmployeeLogin> findByUsername(String username);
    Optional<EmployeeLogin> findByUsernameIgnoreCase(String username);
    List<EmployeeLogin> findByUsernameContaining(String partialUsername);
    List<EmployeeLogin> findByRoleAndEnabledTrue(Role role);
}
