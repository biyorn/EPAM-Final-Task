package com.epam.web.builder.impl;

import com.epam.web.builder.Builder;
import com.epam.web.entity.impl.User;
import com.epam.web.entity.enums.UserRole;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    private static final int BLOCK = 1;

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        User.Builder builder = User.newBuilder();

        long id = resultSet.getLong(User.ID);
        builder.buildId(id);

        String login = resultSet.getString(User.LOGIN);
        builder.buildLogin(login);

        String password = resultSet.getString(User.PASSWORD);
        builder.buildPassword(password);

        BigDecimal balance = resultSet.getBigDecimal(User.BALANCE);
        builder.buildBalance(balance);

        int points = resultSet.getInt(User.POINTS);
        builder.buildPoints(points);

        String role = resultSet.getString(User.ROLE);
        role = role.toUpperCase();
        UserRole userRole = UserRole.valueOf(role);
        builder.buildRole(userRole);

        int value = resultSet.getInt(User.BLOCKED);
        boolean userStatus = value == BLOCK;
        builder.buildBlocked(userStatus);

        return builder.build();
    }
}
