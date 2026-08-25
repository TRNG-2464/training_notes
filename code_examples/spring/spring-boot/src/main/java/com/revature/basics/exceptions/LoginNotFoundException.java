package com.revature.basics.exceptions;

public class LoginNotFoundException extends RuntimeException {
    public LoginNotFoundException(Integer loginId) {
        super("No login details found with ID: " + loginId);
    }

    public LoginNotFoundException(String username) {
        super("No login details found for username: " + username);
    }
}
