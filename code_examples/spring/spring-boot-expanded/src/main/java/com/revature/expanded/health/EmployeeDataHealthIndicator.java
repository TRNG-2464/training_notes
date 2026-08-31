package com.revature.expanded.health;

import com.revature.expanded.repository.EmployeeRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;

public class EmployeeDataHealthIndicator implements HealthIndicator {
    private final EmployeeRepository employeeRepository;

    public EmployeeDataHealthIndicator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Health health() {
        long employeeCount = employeeRepository.count();

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
