package com.revature.expanded.controllers;

import com.revature.expanded.model.Paycheck;
import com.revature.expanded.security.SecurityConfig;
import com.revature.expanded.services.PaycheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaycheckController.class)
@Import(SecurityConfig.class)
public class PaycheckControllerSecuredSliceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaycheckService paycheckService;

    /*
     * NOTE: You can replace this string value with an
     * ObjectMapper from Jackson Fasterxml. The Maven
     * Dependency is as follows:
     * <dependency>
     *      <groupId>tools.jackson.core</groupId>
     *      <artifactId>jackson-databind</artifactId>
     * </dependency>
     */
    private static final String NEW_PAYCHECK_JSON = """
            {
              "empId": 1000,
              "payFromDate": "2026-08-01",
              "payToDate": "2026-08-15",
              "hoursWorked": 80.00,
              "grossPay": 3200.00,
              "totalDeductions": 550.00,
              "netPay": 2650.00
            }
            """;

    /*
     * This Utility method is used to build a dummy Paycheck
     * object for use in my tests.
     *
     * Note: I could have created a 'Paycheck dummyPaycheck'
     * field on this class, then populated it with data in a
     * @BeforeEach setup method instead. However, this utility
     * method serves a similar purpose, and allows me to only
     * call this method when needed (i.e. some tests don't
     * require this dummy data, so I skip the setup for those
     * tests)
     */
    private Paycheck buildDummyPaycheck() {
        Paycheck paycheck = new Paycheck();
        paycheck.setPaycheckId(1);
        paycheck.setEmpId(1000);
        paycheck.setPayFromDate(LocalDate.of(2026, 8, 1));
        paycheck.setPayToDate(LocalDate.of(2026, 8, 15));
        paycheck.setHoursWorked(new BigDecimal("80.00"));
        paycheck.setGrossPay(new BigDecimal("3200.00"));
        paycheck.setTotalDeductions(new BigDecimal("550.00"));
        paycheck.setNetPay(new BigDecimal("2650.00"));
        return paycheck;
    }

    // Tests that an unauthenticated request cannot access this endpoint
    @Test
    void getPaycheckById_withoutAuthentication_returnsUnauthorized() throws Exception {
        mockMvc.perform(get("/paychecks/1"))
                .andExpect(status().isUnauthorized());
    }

    // Tests that an authenticated request can access this endpoint
    @Test
    void getPaycheckById_asAuthenticatedUser_returnsOk() throws Exception {
        Paycheck paycheck = buildDummyPaycheck();
        when(paycheckService.getPaycheckById(1)).thenReturn(paycheck);

        mockMvc.perform(get("/paychecks/1").with(user("joseph").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empId").value(1000))
                .andExpect(jsonPath("$.netPay").value(2650.00));
    }

    // Tests that we can get a paged response from the '/paged' endpoint
    @Test
    void getPaychecksPaged_asAuthenticatedUser_returnsOk() throws Exception {
        Page<Paycheck> page = new PageImpl<>(List.of(buildDummyPaycheck()));
        when(paycheckService.getPaychecksPaged(any())).thenReturn(page);

        mockMvc.perform(get("/paychecks/paged")
                        .with(user("joseph").roles("USER"))
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "grossPay,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    // Tests the service method to get high-earning employees only
    @Test
    void getHighEarningPaychecks_asAuthenticatedUser_returnsOk() throws Exception {
        when(paycheckService.getHighEarningPaychecks(new BigDecimal("5000.00")))
                .thenReturn(List.of(buildDummyPaycheck()));

        mockMvc.perform(get("/paychecks/high-earning")
                        .with(user("joseph").roles("USER"))
                        .param("minGrossPay", "5000.00"))
                .andExpect(status().isOk());
    }

    // Tests a POST request on the "/paychecks" endpoint - verifies creation
    @Test
    void createPaycheck_asAuthenticatedUser_returnsCreated() throws Exception {
        Paycheck request = buildDummyPaycheck();
        request.setPaycheckId(null);
        Paycheck saved = buildDummyPaycheck();
        when(paycheckService.createPaycheck(any())).thenReturn(saved);

        mockMvc.perform(post("/paychecks")
                        .with(user("joseph").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(NEW_PAYCHECK_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.paycheckId").value(1));
    }
}