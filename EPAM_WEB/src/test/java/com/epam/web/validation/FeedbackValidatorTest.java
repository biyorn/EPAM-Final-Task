package com.epam.web.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeedbackValidatorTest {

    private FeedbackValidator validator = new FeedbackValidator();

    @Test
    public void testVerifyReviewShouldReturnTrueWhenValidParameterSupplied() {
        // given
        String review = "It's a nice meal I ate, but it's very expensive";

        // when
        boolean actual = validator.verifyReview(review);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyReviewShouldReturnFalseWhenParameterContainOtherSymbols() {
        // given
        String review = "<Its a good meal>";

        // when
        boolean actual = validator.verifyReview(review);

        // then
        assertFalse(actual);
    }
}
