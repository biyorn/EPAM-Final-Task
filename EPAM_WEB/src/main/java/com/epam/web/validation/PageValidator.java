package com.epam.web.validation;

public class PageValidator {

    private static final String PAGE_REGEX = "^(0*[1-9][0-9]*)$";

    public boolean verifyPage(String page) {
        return page.matches(PAGE_REGEX);
    }
}
