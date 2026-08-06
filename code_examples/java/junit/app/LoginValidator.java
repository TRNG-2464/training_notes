package com.revature.junit.app;

/*
 * LoginValidator is our class that is under test
 *
 * Since our UserDAO is being mocked the mocks will
 * be injected into this object for tests.
 */
public class LoginValidator {

    private final UserDAO userDAO;

    public LoginValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public LoginResult validateLogin(String username, String password) {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            return LoginResult.USER_NOT_FOUND;
        }

        if (user.isAccountLocked()) {
            return LoginResult.ACCOUNT_LOCKED;
        }

        if (!user.getPassword().equals(password)) {
            userDAO.recordFailedLoginAttempt(username);
            return LoginResult.INVALID_PASSWORD;
        }

        return LoginResult.SUCCESS;
    }
}
