package com.revature.expanded.controllers;

import com.revature.expanded.model.Employee;
import com.revature.expanded.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeWebControllerSliceTest {
    @Autowired
    private MockMvc mockMvc;

    /*
     * Note: @Service beans aren't loaded here so the controller
     * needs something to be wired against.
     *
     * @MockitoBean registers a mock EmployeeService directly
     * into this slice's context.
     */
    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void getEmployeeById_returnsOk_evenWithoutAuthentication() throws Exception {
        Employee employee = new Employee(1000, "Jane Doe", "Engineer", new BigDecimal("85000.00"));
        when(employeeService.getEmployeeById(1000)).thenReturn(employee);

        /*
         * Note: since @SecurityConfig is a @Configuration class, this
         * plain test does NOT load it. This request will succeed without
         * additional credentials, even though in the real, wired application
         * it would require authentication for this endpoint.
         *
         * See the 'EmployeeWebControllerSecuredSlideTest' for more details
         */
        mockMvc.perform(get("/employees/1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empName").value("Jane Doe"));
    }
}