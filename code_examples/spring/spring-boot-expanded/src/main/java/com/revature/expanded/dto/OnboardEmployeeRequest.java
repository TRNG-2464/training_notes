package com.revature.expanded.dto;

import com.revature.expanded.model.Employee;
import com.revature.expanded.model.EmployeeLogin;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/*
 * This DTO represents the request to create a new
 * employee and their login details at the same time.
 *
 * Note: You could also use the Employee and EmployeeLogin
 * classes themselves within your controller/service
 *
 * However, creating a dedicated object allows you to
 * better control the boundary between your repository
 * layer and the logic of the rest of your application.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OnboardEmployeeRequest {
    private Employee employee;
    private EmployeeLogin employeeLogin;
}
