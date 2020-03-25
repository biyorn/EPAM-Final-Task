package com.epam.web.validation;

public class LoginValidator {

    private static final String LOGIN_REGEX = "[a-zA-Z]{4,8}";
    private static final String PASSWORD_REGEX = "[\\w]{3,8}";

    public boolean verifyLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public boolean verifyPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}
