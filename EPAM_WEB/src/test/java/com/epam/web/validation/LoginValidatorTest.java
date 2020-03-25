package com.epam.web.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginValidatorTest {

    private LoginValidator validator = new LoginValidator();

    @Test
    public void testVerifyLoginShouldReturnTrueWhenValidLoginSupplied() {
        // given
        String login = "user";

        // when
        boolean actual = validator.verifyLogin(login);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyLoginShouldReturnFalseWhenLoginContainNumbers() {
        // given
        String login = "user12";

        // when
        boolean actual = validator.verifyLogin(login);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyPasswordShouldReturnTrueWhenValidPassSupplied() {
        // given
        String password = "12ab";

        // when
        boolean actual = validator.verifyPassword(password);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyPasswordShouldReturnFalseWhenPassMoreThanEight() {
        // given
        String password = "012345678";

        // when
        boolean actual = validator.verifyPassword(password);

        // then
        assertFalse(actual);
    }
}
