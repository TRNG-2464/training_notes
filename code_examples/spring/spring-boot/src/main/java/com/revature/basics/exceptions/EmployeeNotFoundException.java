package com.revature.basics.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException (Integer employeeId) {
        super("No Employee found with ID: " + employeeId);
    }
}
