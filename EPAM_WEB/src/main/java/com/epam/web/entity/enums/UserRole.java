package com.epam.web.entity.enums;

public enum UserRole {
    ADMIN("admin"),
    MANAGER("manager"),
    USER("user");

    private String title;

    UserRole(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
