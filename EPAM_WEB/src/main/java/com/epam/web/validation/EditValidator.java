package com.epam.web.validation;

public class EditValidator {

    private static final String BALANCE_REGEX = "[\\d]{1,6}[\\.]?[\\d]{1,2}";
    private static final String POINTS_REGEX = "[\\d]{1,2}";
    private static final String ID_REGEX = "^[1-9][\\d]*$";

    public boolean verifyBalance(String balance) {
        return balance.matches(BALANCE_REGEX);
    }

    public boolean verifyPoints(String points) {
        return points.matches(POINTS_REGEX);
    }

    public boolean verifyId(String id) {
        return id.matches(ID_REGEX);
    }
}
