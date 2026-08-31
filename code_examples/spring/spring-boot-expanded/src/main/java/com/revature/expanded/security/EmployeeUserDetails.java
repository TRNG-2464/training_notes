package com.revature.expanded.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.revature.expanded.model.EmployeeLogin;

import java.util.Collection;
import java.util.List;

/*
 * This class showcases the 'Adapter' design pattern.
 *
 * An 'Adapter' acts as a middle-man between two expected
 * structures. In this case - it bridges the gap between
 * the EmployeeLogin database entity and the UserDetails
 * Spring Security's 'user' representation
 *
 * NOTE: The EmployeeLogin class was intentionally designed
 * to be similar to the UserDetails of Spring Security.
 * An Alternative approach is to simply implement UserDetails
 * on the EmployeeLogin entity, and use that as part of
 * Spring Security.
 *
 * It is more common; however, to separate your database
 * entities from your Security entities. This also gives
 * you an opportunity to view the Adapter pattern in action!
 */
@AllArgsConstructor
public class EmployeeUserDetails implements UserDetails {
    private final EmployeeLogin employeeLogin;

    @Override
    public String getUsername() {
        return employeeLogin.getUsername();
    }

    @Override
    public String getPassword() {
        return employeeLogin.getPassword();
    }

    @Override
    public boolean isEnabled() {
        return employeeLogin.getEnabled();
    }

    /*
     * The following code is used to translate the Role enum
     * into a "GrantedAuthority" object. 'GrantedAuthority' is
     * Spring Security's built-in mechanism to provide role-based
     * authorization details.
     *
     * Spring Security's 'SimpleGrantedAuthority' expects string
     * values that are prefixed with "ROLE_". This matches how
     * we've composed the Role enum.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(employeeLogin.getRole().name())
        );
    }

    /*
     * The following three methods all return true since the
     * EmployeeLogin class does not have a mechanism to track
     * these states. Your application can (and should) be
     * more robust than this example
     */
    @Override   // Has this account expired?
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override   // Is this account locked?
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override   // Is this account's credentials expired?
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
