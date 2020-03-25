package com.epam.web.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardValidatorTest {

    private CardValidator validator = new CardValidator();

    @Test
    public void testVerifyCardNumberShouldReturnTrueWhenValidCardNumberSupplied() {
        // given
        String validCardNumber = "1234567891012134";

        // when
        boolean actual = validator.verifyCardNumber(validCardNumber);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyCardNumberShouldReturnFalseWhenWrongCardNumberSuppliedContainLetters() {
        // given
        String wrongCardNumber = "123344ff4333edd4";

        // when
        boolean actual = validator.verifyCardNumber(wrongCardNumber);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyMonthShouldReturnTrueWhenValidMonthSupplied() {
        // given
        String month = "11";

        // when
        boolean actual = validator.verifyMonth(month);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyMonthShouldReturnFalseWhenWrongParameterSupplied() {
        // given
        String month = "13";

        // when
        boolean actual = validator.verifyMonth(month);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyYearShouldReturnTrueWhenValidParametersSupplied() {
        // given
        String year = "22";

        // when
        boolean actual = validator.verifyYear(year);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyYearShouldReturnFalseWhenYearLessThanTwenty() {
        // given
        String year = "19";

        // when
        boolean actual = validator.verifyYear(year);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyCvsShouldReturnTrueWhenValidParametersSupplied() {
        // given
        String cvs = "123";

        // when
        boolean actual = validator.verifyCvs(cvs);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyCvsShouldReturnFalseWhenParameterContainsLetter() {
        // given
        String cvs = "1f3";

        // when
        boolean actual = validator.verifyCvs(cvs);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyAmountShouldReturnTrueWhenValidParameterSupplied() {
        // given
        String amount = "11.22";

        // when
        boolean actual = validator.verifyAmount(amount);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyAmountShouldReturnFalseWhenParameterNegative() {
        // given
        String amount = "-11.22";

        // when
        boolean actual = validator.verifyAmount(amount);

        // then
        assertFalse(actual);
    }
}
