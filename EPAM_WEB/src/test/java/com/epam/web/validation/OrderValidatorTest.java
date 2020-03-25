package com.epam.web.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class OrderValidatorTest {

    private OrderValidator validator = new OrderValidator();

    @Test
    public void testVerifyIdShouldReturnTrueWhenPositiveNumberSupplied() {
        // given
        String id = "3";

        // when
        boolean actual = validator.verifyId(id);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyIdShouldReturnFalseWhenLetterSupplied() {
        // given
        String id = "g";

        // when
        boolean actual = validator.verifyId(id);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyPriceShouldReturnTrueWhenValidPriceSupplied() {
        // given
        String price = "22.3";

        // when
        boolean actual = validator.verifyPrice(price);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyPriceShouldReturnFalseWhenNegativeNumberSupplied() {
        // given
        String price = "-22.3";

        // when
        boolean actual = validator.verifyPrice(price);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyTimeShouldReturnTrueWhenValidTimeSupplied() {
        // given
        String time = "18:44";

        // when
        boolean actual = validator.verifyTime(time);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyTimeShouldReturnTrueWhenTimeEmpty() {
        // given
        String time = "";

        // when
        boolean actual = validator.verifyTime(time);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyTimeShouldReturnFalseWhenTimeContainLetter() {
        // given
        String time = "P18:44";

        // when
        boolean actual = validator.verifyTime(time);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyPaymentShouldReturnTrueWhenAccountSupplied() {
        // given
        String payment = "account";

        // when
        boolean actual = validator.verifyPayment(payment);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyPaymentShouldReturnTrueWhenCashSupplied() {
        // given
        String payment = "cash";

        // when
        boolean actual = validator.verifyPayment(payment);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyPaymentShouldReturnFalseWhenPaymentContainAlmostSimilarAsAccount() {
        // given
        String payment = "accoun";

        // when
        boolean actual = validator.verifyPayment(payment);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyAmountShouldReturnTrueWhenAmountPositiveAndLessThanHundred() {
        // given
        String amount = "24";

        // when
        boolean actual = validator.verifyAmount(amount);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyAmountShouldReturnFalseWhenAmountMoreThatHundred() {
        // given
        String amount = "101";

        // when
        boolean actual = validator.verifyAmount(amount);

        // then
        assertFalse(actual);
    }
}
