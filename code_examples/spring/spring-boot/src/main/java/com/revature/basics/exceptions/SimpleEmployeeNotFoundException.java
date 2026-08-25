package com.revature.basics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * This Custom Exception class showcases the '@ResponseStatus'
 * annotation, which is used to map a specific status code to
 * this response whenever this exception goes unhandled. This
 * is typically best suited for cases where Spring's default
 * error body is good enough, and no other custom response
 * formatting is required.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SimpleEmployeeNotFoundException extends RuntimeException {
    public SimpleEmployeeNotFoundException(Integer employeeId) {
        super("[SimpleEmployeeNotFoundException] No employee found with ID: " + employeeId);
    }
}
