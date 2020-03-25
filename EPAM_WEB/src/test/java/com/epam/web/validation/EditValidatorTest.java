package com.epam.web.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EditValidatorTest {

    private EditValidator validator = new EditValidator();

    @Test
    public void testVerifyBalanceShouldReturnTrueWhenValidParameterSupplied() {
        // given
        String balance = "22.33";

        // when
        boolean actual = validator.verifyBalance(balance);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyBalanceShouldReturnFalseWhenBalanceNegative() {
        // given
        String balance = "-22.33";

        // when
        boolean actual = validator.verifyBalance(balance);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyPointsShouldReturnTrueWhenValidParameterSupplied() {
        // given
        String points = "10";

        // when
        boolean actual = validator.verifyPoints(points);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyPointsShouldReturnFalseWhenNumberThreeDigit() {
        // given
        String points = "111";

        // when
        boolean actual = validator.verifyPoints(points);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyIdShouldReturnTrueWhenIdPositive() {
        // given
        String id = "20";

        // when
        boolean actual = validator.verifyId(id);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyIdShouldReturnFalseWhenIdEqualZero() {
        // given
        String id = "0";

        // when
        boolean actual = validator.verifyId(id);

        // then
        assertFalse(actual);
    }
}
