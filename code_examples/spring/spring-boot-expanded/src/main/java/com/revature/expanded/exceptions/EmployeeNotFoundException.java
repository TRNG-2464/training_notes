package com.revature.expanded.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException (Integer employeeId) {
        super("No Employee found with ID: " + employeeId);
    }
}
