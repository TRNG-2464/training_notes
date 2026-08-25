package com.revature.basics.repository;

import com.revature.basics.model.EmployeeLogin;
import com.revature.basics.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeLoginRepository extends JpaRepository<EmployeeLogin, Integer> {

    /*
     * Custom Query Method - simple string query
     */
    Optional<EmployeeLogin> findByUsername(String username);

    /*
     * Custom Query method to look up a login by username
     * without requiring exact casing.
     */
    Optional<EmployeeLogin> findByUsernameIgnoreCase(String username);

    /*
     * CustomQuery method to look up a login by username using
     * partial matching
     */
    List<EmployeeLogin> findByUsernameContaining(String partialUsername);

    /*
     * Custom Query method which combines a property expression with
     * a keyword, filtering by an exact match on "role" combined with
     * a boolean field value
     */
    List<EmployeeLogin> findByRoleAndEnabledTrue(Role role);
}
