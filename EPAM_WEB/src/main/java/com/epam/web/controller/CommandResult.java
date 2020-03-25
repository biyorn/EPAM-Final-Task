package com.epam.web.controller;

public class CommandResult {

    private String page;
    private boolean isForward;

    private CommandResult(String page, boolean isForward) {
        this.page = page;
        this.isForward = isForward;
    }

    public String getPage() {
        return page;
    }

    public boolean isForward() {
        return isForward;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, true);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, false);
    }
}
