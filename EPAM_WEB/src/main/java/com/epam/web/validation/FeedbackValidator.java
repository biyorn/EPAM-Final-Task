package com.epam.web.validation;

public class FeedbackValidator {

    private static final String REVIEW_REGEX = "[\\w\\s\\'\\.\\,\\!\\:\\;]{1,255}";

    public boolean verifyReview(String review) {
        return review.matches(REVIEW_REGEX);
    }
}
