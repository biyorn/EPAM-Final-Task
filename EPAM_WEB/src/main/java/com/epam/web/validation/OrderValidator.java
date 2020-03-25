package com.epam.web.validation;

public class OrderValidator {

    private static final String ID_REGEX = "[\\d]+";
    private static final String PRICE_REGEX = "[\\d]*[\\.]?[\\d]{1,2}";
    private static final String TIME_REGEX = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$|^$";
    private static final String PAYMENT_REGEX = "account|cash";
    private static final String AMOUNT_REGEX = "0[1-9]|[1-9]|[1-9][0-9]";

    public boolean verifyId(String id) {
        return id.matches(ID_REGEX);
    }

    public boolean verifyPrice(String price) {
        return price.matches(PRICE_REGEX);
    }

    public boolean verifyTime(String time) {
        return time.matches(TIME_REGEX);
    }

    public boolean verifyPayment(String payment) {
        return payment.matches(PAYMENT_REGEX);
    }

    public boolean verifyAmount(String amount) {
        return amount.matches(AMOUNT_REGEX);
    }
}
