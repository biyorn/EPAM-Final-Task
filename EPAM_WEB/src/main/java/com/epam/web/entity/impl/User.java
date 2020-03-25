package com.epam.web.entity.impl;

import com.epam.web.entity.Identifable;
import com.epam.web.entity.enums.UserRole;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Identifable, Serializable {

    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "pass";
    public static final String BALANCE = "balance";
    public static final String POINTS = "points";
    public static final String ROLE = "role";
    public static final String BLOCKED = "blocked";

    private long id;
    private String login;
    private String password;
    private BigDecimal balance;
    private int points;
    private UserRole role;
    private boolean blocked;

    private User() {}

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public int getPoints() {
        return points;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public class Builder {
        private Builder() {}

        public Builder buildId(long id) {
            User.this.id = id;
            return this;
        }

        public Builder buildLogin(String login) {
            User.this.login = login;
            return this;
        }

        public Builder buildPassword(String password) {
            User.this.password = password;
            return this;
        }

        public Builder buildBalance(BigDecimal balance) {
            User.this.balance = balance;
            return this;
        }

        public Builder buildPoints(int points) {
            User.this.points = points;
            return this;
        }

        public Builder buildRole(UserRole role) {
            User.this.role = role;
            return this;
        }

        public Builder buildBlocked(boolean blocked) {
            User.this.blocked = blocked;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}
