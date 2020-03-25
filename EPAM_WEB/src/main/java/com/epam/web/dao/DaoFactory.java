package com.epam.web.dao;

import com.epam.web.dao.impl.MealDaoImpl;
import com.epam.web.dao.impl.OrderDaoImpl;
import com.epam.web.dao.impl.OrderMealDaoImpl;
import com.epam.web.dao.impl.UserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    private Connection connection;

    public DaoFactory(Connection connection) {
        this.connection = connection;
    }

    public UserDaoImpl createUserDaoImpl() {
        return new UserDaoImpl(connection);
    }

    public MealDaoImpl createMealDaoImpl() {
        return new MealDaoImpl(connection);
    }

    public OrderMealDaoImpl createOrderMealDaoImpl() {
        return new OrderMealDaoImpl(connection);
    }

    public OrderDaoImpl createOrderDaoImpl() {
        return new OrderDaoImpl(connection);
    }

    public void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void endTransaction() throws SQLException {
        connection.setAutoCommit(true);
        connection.commit();
    }
}
