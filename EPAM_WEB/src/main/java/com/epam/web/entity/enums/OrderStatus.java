package com.epam.web.entity.enums;

public enum OrderStatus {

    NEW("new"),
    PAID("paid"),
    CANCEL("cancel");

    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
