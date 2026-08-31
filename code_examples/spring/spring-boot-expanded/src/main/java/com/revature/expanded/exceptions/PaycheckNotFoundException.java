package com.revature.expanded.exceptions;

public class PaycheckNotFoundException extends RuntimeException {
    public PaycheckNotFoundException(Integer paycheckId) {
        super("No Paycheck found with ID: " + paycheckId);
    }
}
