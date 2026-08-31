package com.revature.expanded.health.empoyees;

import com.revature.expanded.repository.EmployeeRepository;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EmployeeInfoContributor implements InfoContributor {
    private final EmployeeRepository employeeRepository;

    public EmployeeInfoContributor(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("employeeData", Map.of(
                "currentEmployeeCount", employeeRepository.count()
        ));
    }
}
