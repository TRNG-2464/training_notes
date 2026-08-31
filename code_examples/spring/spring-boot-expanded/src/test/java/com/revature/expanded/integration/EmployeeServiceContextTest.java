package com.revature.expanded.integration;

import com.revature.expanded.model.Employee;
import com.revature.expanded.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/*
 * The following shows a very simple integration test using
 * @SpringBootTest
 *
 * The @SpringBootTest annotation loads the actual Spring Context
 * (ApplicationContext) for use in this test class. This means
 * your actual application, including all beans, configuration,
 * and auto-config will be loaded as well.
 *
 * Here 'webEnvironment' with RANDOM_PORT starts a real embedded server
 * on an actually (randomly chosen) port. This lets tests send genuine
 * HTTP requests.
 *
 * @AutoConfigureTestRestTemplate: This annotation (as it's name suggests)
 * automatically configures the 'TestRestTemplate' - i.e. the
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
public class EmployeeServiceContextTest {

    /*
     * TestRestTemplate is a class that is used in tests to simulate
     * requests send to an application. These requests can even include
     * headers, for authentication, if needed
     */
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeService employeeService;

    /*
     * This is an incredibly simple integration test - it calls a
     * service method directly, checking that we are getting data
     * from our database
     *
     * This test doesn't use the TestRestTemplate
     */
    @Test
    void getAllEmployees_returnsEmployeesFromRealContext() {
        List<Employee> employees = employeeService.getAllEmployees();

        assertThat(employees).isNotNull();
    }

    /*
     * This test sends a response to our application (same endpoint
     * as above) but includes a header with actual authentication details.
     *
     * This allows us to examine the full application's execution
     */
    @Test
    void getAllEmployees_returnsOkResponse() {
        ResponseEntity<Employee[]> response = restTemplate
                .withBasicAuth("joseph", "admin123") // we supply 'real' credentials here
                .getForEntity("/employees", Employee[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
