package com.revature.actuator.empoyees;

import com.revature.basics.repository.EmployeeRepository;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
 * A Custom InfoContributor. This adds dynamic information to
 * our /actuator/info endpoint -- specifically, this adds a
 * live 'employee count', which an info.* property in the
 * applications.properties file could not do since it cannot
 * reflect data that changes during runtime.
 */
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
