package com.epam.web.validation;

public class CardValidator {

    private static final String CARD_REGEX = "[\\d]{16}";
    private static final String MONTH_REGEX = "(0[1-9]|10|11|12)";
    private static final String YEAR_REGEX = "[2-9][0-9]";
    private static final String CVS_REGEX = "[\\d]{3}";
    private static final String AMOUNT_REGEX = "[\\d]{1,6}[\\.]?[\\d]{1,2}";

    public boolean verifyCardNumber(String card) {
        return card.matches(CARD_REGEX);
    }

    public boolean verifyMonth(String month) {
        return month.matches(MONTH_REGEX);
    }

    public boolean verifyYear(String year) {
        return year.matches(YEAR_REGEX);
    }

    public boolean verifyCvs(String cvs) {
        return cvs.matches(CVS_REGEX);
    }

    public boolean verifyAmount(String amount) {
        return amount.matches(AMOUNT_REGEX);
    }
}
