package com.revature.basics.exceptions;

public class PaycheckNotFoundException extends RuntimeException {
    public PaycheckNotFoundException(Integer paycheckId) {
        super("No Paycheck found with ID: " + paycheckId);
    }
}
