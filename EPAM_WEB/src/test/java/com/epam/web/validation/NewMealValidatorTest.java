package com.epam.web.validation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NewMealValidatorTest {

    private NewMealValidator validator = new NewMealValidator();

    @Test
    public void testVerifyNameShouldReturnTrueWhenValidNameSupplied() {
        // given
        String name = "bread";

        // when
        boolean actual = validator.verifyName(name);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyNameShouldReturnFalseWhenNameContainNumbers() {
        // given
        String name = "bread12";

        // when
        boolean actual = validator.verifyName(name);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyDescriptionShouldReturnTrueWhenValidDescriptionSupplied() {
        // given
        String description = "A meal is an eating occasion that takes place at a certain time and includes prepared food";

        // when
        boolean actual = validator.verifyDescription(description);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyDescriptionShouldReturnFalseWhenDescriptionLessThanTenSymbols() {
        // given
        String description = "1231df";

        // when
        boolean actual = validator.verifyDescription(description);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyPriceShouldReturnTrueWhenValidPriceSupplied() {
        // given
        String price = "11.22";

        // when
        boolean actual = validator.verifyPrice(price);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyPriceShouldReturnFalseWhenPriceNegative() {
        // given
        String price = "-11.22";

        // when
        boolean actual = validator.verifyPrice(price);

        // then
        assertFalse(actual);
    }

    @Test
    public void testVerifyImageShouldReturnTrueWhenImageContainDotJpg() {
        // given
        String image = "first.jpg";

        // when
        boolean actual = validator.verifyImage(image);

        // then
        assertTrue(actual);
    }

    @Test
    public void testVerifyImageShouldReturnFalseWhenImageContainDotTxt() {
        // given
        String image = "second.txt";

        // when
        boolean actual = validator.verifyImage(image);

        // then
        assertFalse(actual);
    }
}
