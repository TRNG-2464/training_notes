package com.revature.expanded.controllers;

import com.revature.expanded.model.Employee;
import com.revature.expanded.security.SecurityConfig;
import com.revature.expanded.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;

/*
 * The '@Import' annotation is used to explicitly bring in the
 * real security configurations to this slice.
 */
@WebMvcTest(EmployeeController.class)
@Import(SecurityConfig.class)
public class EmployeeWebControllerSecuredSliceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    /*
     * With the security Configurations imported, we can verify that
     * unauthorized requests return with an unauthorized status
     */
    @Test
    void getEmployeeById_withoutAuthentication_returnsUnauthorized() throws Exception {
        mockMvc.perform(get("/employees/1000"))
                .andExpect(status().isUnauthorized());
    }

    /*
     * The following test uses chains the get method request '.with()' a
     * 'user' object. This object represents an Authenticated used for our
     * application (we give this user the 'ADMIN' role arbitrarily)
     */
    @Test
    void getEmployeeById_withAuthentication_returnsOk() throws Exception {
        Employee employee = new Employee(1000, "Joseph", "Developer", new BigDecimal("85000.00"));
        when(employeeService.getEmployeeById(1000)).thenReturn(employee);

        mockMvc.perform(get("/employees/1000").with(user("admin1").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empName").value("Joseph"));
    }
}
