package com.epam.web.validation;

public class NewMealValidator {

    private static final String NAME_REGEX = "[a-zA-Z\\s]{5,30}";
    private static final String DESCRIPTION_REGEX = "[\\w\\s\\.\\,]{10,180}";
    private static final String PRICE_REGEX = "[\\d]*[\\.]?[\\d]{1,2}";
    private static final String IMAGE_REGEX = "^.+\\.jpg$";

    public boolean verifyName(String name) {
        return name.matches(NAME_REGEX);
    }

    public boolean verifyDescription(String description) {
        return description.matches(DESCRIPTION_REGEX);
    }

    public boolean verifyPrice(String price) {
        return price.matches(PRICE_REGEX);
    }

    public boolean verifyImage(String image) {
        return image.matches(IMAGE_REGEX);
    }
}
