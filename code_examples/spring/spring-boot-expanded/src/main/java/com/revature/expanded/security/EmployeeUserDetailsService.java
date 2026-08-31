package com.revature.expanded.security;

import com.revature.expanded.model.EmployeeLogin;
import com.revature.expanded.repository.EmployeeLoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {

    private final EmployeeLoginRepository employeeLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
         * Search my own database for a user with the given string username
         */
        EmployeeLogin employeeLogin = employeeLoginRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "No user found with username: " + username
                ));

        // Return our Adapter - providing the details from the EmployeeLogin database record
        return new EmployeeUserDetails(employeeLogin);
    }
}
