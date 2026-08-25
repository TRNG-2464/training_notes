package com.revature.basics.exceptions;

/*
 * This custom Exception class is used to showcase the use of a
 * simple ExceptionHandler and ResponseStatus within a controller
 *
 * See the 'BasicExceptionHandlerController' for more details
 */
public class OtherEmployeeNotFoundException extends RuntimeException {
    public OtherEmployeeNotFoundException(Integer employeeId) {
        super("[OtherEmployeeNotFoundException] No Employee Found with Id: " + employeeId);
    }
}
