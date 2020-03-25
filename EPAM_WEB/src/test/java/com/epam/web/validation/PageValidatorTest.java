package com.epam.web.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PageValidatorTest {

    private PageValidator validator = new PageValidator();

    @Test
    public void testVerifyPageShouldReturnTrueWhenValidPageSupplied() {
        // given
        String page = "3";

        // when
        boolean actual = validator.verifyPage(page);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyPageShouldReturnFalseWhenPageContainLetter() {
        // given
        String page = "d";

        // when
        boolean actual = validator.verifyPage(page);

        // then
        assertFalse(actual);
    }
}
