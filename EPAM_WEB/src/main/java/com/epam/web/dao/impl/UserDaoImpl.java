package com.epam.web.dao.impl;

import com.epam.web.builder.impl.UserBuilder;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.UserDao;
import com.epam.web.entity.enums.UserRole;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.DaoException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM users WHERE login = ? AND pass = ?";
    private static final String SAVE_USER =
            "INSERT INTO users (login, pass, points, balance, role, blocked) values(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_POINTS = "UPDATE users SET points = ? WHERE id = ?";
    private static final String UPDATE_USER_BALANCE = "UPDATE users SET balance = ? WHERE id = ?";
    private static final String UPDATE_USER_BLOCK = "UPDATE users SET blocked = ? WHERE id = ?";


    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(
                FIND_BY_LOGIN_AND_PASSWORD,
                new UserBuilder(),
                login,
                password
        );
    }

    @Override
    public void editPoints(User user) throws DaoException {
        int points = user.getPoints();
        String tempPoints = String.valueOf(points);
        long id = user.getId();
        String tempId = String.valueOf(id);
        executeUpdate(UPDATE_USER_POINTS, tempPoints, tempId);
    }

    @Override
    public void editBalance(User user) throws DaoException {
        BigDecimal balance = user.getBalance();
        String tempBalance = String.valueOf(balance);
        long id = user.getId();
        String tempId = String.valueOf(id);
        executeUpdate(UPDATE_USER_BALANCE, tempBalance, tempId);
    }

    @Override
    public void editBlock(User user) throws DaoException {
        int block = user.isBlocked() ? 1 : 0;
        String tempBlock = String.valueOf(block);
        long id = user.getId();
        String tempId = String.valueOf(id);
        executeUpdate(UPDATE_USER_BLOCK, tempBlock, tempId);
    }

    @Override
    public void save(User user) throws DaoException {
        String login = user.getLogin();
        String password = user.getPassword();
        int points = user.getPoints();
        String tempPoints = String.valueOf(points);
        BigDecimal balance = user.getBalance();
        String tempBalance = String.valueOf(balance);
        UserRole role = user.getRole();
        String tempRole = role.getTitle();
        int block = user.isBlocked() ? 1 : 0;
        String tempBlock = String.valueOf(block);
        executeUpdate(SAVE_USER, login, password, tempPoints, tempBalance, tempRole, tempBlock);
    }

    @Override
    protected String getTableName() {
        return "users";
    }
}
