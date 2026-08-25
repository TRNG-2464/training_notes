package com.revature.actuator;

import com.revature.basics.repository.EmployeeRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;

/*
 * This showcases a custom Health Indicator. The default behavior
 * for an 'UP' status on the database only checks that the
 * database CONNECTION is working. This custom health indicator
 * confirms information about the Data itself (are there records
 * in the Employee database)
 */
public class EmployeeDataHealthIndicator implements HealthIndicator {
    private final EmployeeRepository employeeRepository;

    public EmployeeDataHealthIndicator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Health health() {
        long employeeCount = employeeRepository.count();

        // Health uses the builder pattern to build the Up/down status'
        // the 'withDetail' key/value pairs are shown in the endpoint when
        // 'show-details' is enabled in the application.properties config
        if (employeeCount == 0) {
            return Health.down()
                    .withDetail("reason", "No Employee Records Found")
                    .withDetail("employeeCount", employeeCount)
                    .build();
        }

        return Health.up()
                .withDetail("employeeCount", employeeCount)
                .build();
    }
}
