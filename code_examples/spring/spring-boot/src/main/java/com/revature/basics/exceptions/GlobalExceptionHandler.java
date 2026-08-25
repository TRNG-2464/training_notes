package com.revature.basics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * This Global controller uses the @RestControllerAdvice annotation
 *
 * This annotation is a combination of @ControllerAdvice + @ResponseBody
 * It acts as a @ControllerAdvice - handling any exceptions thrown matching
 * the types specified within each @ExceptionHandler
 *
 * This logic handles exceptions thrown by any controllers in our application.
 *
 * This is a common pattern used to reduce duplication of logic across
 * our application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Runs when any controller throws an 'EmployeeNotFoundException'
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFound(EmployeeNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // Runs when any controller throws a 'PaycheckNotFoundException'
    @ExceptionHandler(PaycheckNotFoundException.class)
    public ResponseEntity<String> handlePaycheckNotFound(PaycheckNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // Runs when any controller throws a 'LoginNotFoundException'
    @ExceptionHandler(LoginNotFoundException.class)
    public ResponseEntity<String> handleLoginNotFound(LoginNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    // Runs when any controller throws an 'IllegalArgumentException'
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid request: " + ex.getMessage());
    }

    // This acts as a 'catch-all' handler for any exception not specifically
    // handled above. Though this can be a reasonable safety net it is recommended
    // that you explicitly handle known & excepted failure conditions when possible
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred.");
    }
}
