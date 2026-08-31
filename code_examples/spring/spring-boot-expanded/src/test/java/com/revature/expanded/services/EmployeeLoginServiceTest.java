package com.revature.expanded.services;

import com.revature.expanded.exceptions.LoginNotFoundException;
import com.revature.expanded.model.EmployeeLogin;
import com.revature.expanded.repository.EmployeeLoginRepository;
import com.revature.expanded.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/*
 * The following class illustrates a common, simple Unit Test
 * Class within a Spring Boot application - testing the business
 * logic layer of an application.
 *
 * To test some classes (such as Controllers), will require
 * Spring Context - so it is, at minimum, an Integration Test
 */
@ExtendWith(MockitoExtension.class)
class EmployeeLoginServiceTest {

    @Mock
    private EmployeeLoginRepository employeeLoginRepository;

    @InjectMocks
    private EmployeeLoginService employeeLoginService;

    private EmployeeLogin login1;
    private EmployeeLogin login2;

    @BeforeEach
    void setUp() {
        /*
         * Here, we create an actual EmployeeLogin object since
         * there is no fear of manipulating another system using
         * this class directly.
         *
         * This will be used to validate some of our methods
         */
        login1 = new EmployeeLogin(1, 1000, "jdoe", "jdoePw", Role.ROLE_ADMIN, true);
        login2 = new EmployeeLogin(2, 2000, "idoe", "idoePw", Role.ROLE_MANAGER, true);
    }

    @Test
    void getAllLogins_returnsAllLoginsFromRepository() {
        /*
         * This method tests that the Service method returns a list
         * from the Repository layer through correctly calling the
         * findAll() method on the repository
         */
        List<EmployeeLogin> logins = List.of(login1, login2);
        when(employeeLoginRepository.findAll()).thenReturn(logins);

        List<EmployeeLogin> result = employeeLoginService.getAllLogins();

        assertThat(result).isEqualTo(logins);

        /*
         * This is the important part of this test. verify confirms that
         * the Repository was actually called (i.e. the behavior of our
         * application functioned correctly)
         */
        verify(employeeLoginRepository).findAll();
    }

    @Test
    void getLoginByUsername_found_returnsLogin() {
        /*
         * This method is checking that the service is correctly
         * passing the string parameter to the correct method call
         * on the repository
         */
        when(employeeLoginRepository.findByUsername("jdoe")).thenReturn(Optional.of(login1));

        EmployeeLogin result = employeeLoginService.getLoginByUsername("jdoe");

        assertThat(result).isEqualTo(login1);
        verify(employeeLoginRepository).findByUsername("jdoe");
    }

    @Test
    void getLoginByUsername_notFound_throwsLoginNotFoundException() {
        /*
         * This method is checking that the service is correctingly
         * throwing an exception when given an empty return value
         * back from the repository
         */
        when(employeeLoginRepository.findByUsername("empty")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeLoginService.getLoginByUsername("empty"))
                .isInstanceOf(LoginNotFoundException.class);
    }

    @Test
    void getLoginByUsernameIgnoreCase_found_returnsLogin() {
        /*
         * This method is checking the service is correctly passing
         * in the argument to the repository method, and getting
         * the expected response.
         */
        when(employeeLoginRepository.findByUsernameIgnoreCase("JDoe")).thenReturn(Optional.of(login1));

        EmployeeLogin result = employeeLoginService.getLoginByUsernameIgnoreCase("JDoe");

        assertThat(result).isEqualTo(login1);
        verify(employeeLoginRepository).findByUsernameIgnoreCase("JDoe");
    }

    @Test
    void getLoginByUsernameIgnoreCase_notFound_throwsLoginNotFoundException() {
        /*
         * This method is checking the 'failure' path for the
         * 'findByUsernameIgnoreCase' method - i.e. it is throwing
         * an exception correctly
         */
        when(employeeLoginRepository.findByUsernameIgnoreCase("empty")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeLoginService.getLoginByUsernameIgnoreCase("empty"))
                .isInstanceOf(LoginNotFoundException.class);
    }

    @Test
    void getActiveLoginsByRole_returnsMatchingLogins() {
        // Here, we only return Login1, because we expect this test to only find Admins
        List<EmployeeLogin> logins = List.of(login1);
        when(employeeLoginRepository.findByRoleAndEnabledTrue(Role.ROLE_ADMIN)).thenReturn(logins);

        List<EmployeeLogin> result = employeeLoginService.getActiveLoginsByRole(Role.ROLE_ADMIN);

        assertThat(result).isEqualTo(logins);
        verify(employeeLoginRepository).findByRoleAndEnabledTrue(Role.ROLE_ADMIN);
    }

    @Test
    void isOwnRecord_usernameFoundAndEmpIdMatches_returnsTrue() {
        /*
         * This method checking that the service method is correctly
         * returning true when checking the username against it's id
         */
        when(employeeLoginRepository.findByUsername("jdoe")).thenReturn(Optional.of(login1));

        boolean result = employeeLoginService.isOwnRecord("jdoe", 1000);

        assertThat(result).isTrue();
        verify(employeeLoginRepository).findByUsername("jdoe");
    }

    @Test
    void isOwnRecord_usernameFoundButEmpIdDoesNotMatch_returnsFalse() {
        /*
         * This method checks the 'failure path' for checking if a
         * record's username matches it's ID (i.e. the username was found
         * but the wrong ID is given)
         */
        when(employeeLoginRepository.findByUsername("jdoe")).thenReturn(Optional.of(login1));

        boolean result = employeeLoginService.isOwnRecord("jdoe", 9999);

        assertThat(result).isFalse();
        verify(employeeLoginRepository).findByUsername("jdoe");
    }

    @Test
    void isOwnRecord_usernameNotFound_returnsFalse() {
        /*
         * This method checks that, when the 'isOwnRecord' is called
         * and the username is not found, the method correctly return
         * false
         */
        when(employeeLoginRepository.findByUsername("empty")).thenReturn(Optional.empty());

        boolean result = employeeLoginService.isOwnRecord("empty", 1000);

        assertThat(result).isFalse();
        verify(employeeLoginRepository).findByUsername("empty");
    }
}
