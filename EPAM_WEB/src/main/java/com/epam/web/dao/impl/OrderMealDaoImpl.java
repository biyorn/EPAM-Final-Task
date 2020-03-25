package com.epam.web.dao.impl;

import com.epam.web.builder.impl.OrderMealBuilder;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.OrderMealDao;
import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.entity.impl.Meal;
import com.epam.web.entity.impl.OrderMeal;
import com.epam.web.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderMealDaoImpl extends AbstractDao<OrderMeal> implements OrderMealDao {

    private static final String INSERT_ORDER_MEAL =
            "INSERT INTO order_meal(order_id,meal_id, amount) VALUES(LAST_INSERT_ID(),?,?)";
    private static final String SELECT_USER_ORDERS =
            "SELECT order_id, user_id, points, login, meal_id, amount, " +
                    "time, status, review, payment, image, name, description, price " +
                    "FROM order_meal JOIN orders ON orders.id = order_id " +
                    "JOIN meals ON meals.id = meal_id " +
                    "JOIN users ON orders.user_id = users.id " +
                    "WHERE user_id = ? ORDER BY orders.id DESC";
    private static final String SELECT_ORDERS =
            "SELECT order_id, user_id, points, login, meal_id, amount, " +
                    "time, status, review, payment, image, name, description, price " +
                    "FROM order_meal JOIN orders ON orders.id = order_id " +
                    "JOIN meals ON meals.id = meal_id " +
                    "JOIN users ON users.id = orders.user_id " +
                    "WHERE orders.status = ?";

    public OrderMealDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<OrderMeal> findOrdersByStatus(OrderStatus status) throws DaoException {
        String local = status.getTitle();
        return executeQuery(SELECT_ORDERS, new OrderMealBuilder(), local);
    }

    @Override
    public List<OrderMeal> findUserOrdersById(Long id) throws DaoException {
        return executeQuery(SELECT_USER_ORDERS, new OrderMealBuilder(), String.valueOf(id));
    }

    @Override
    public void save(OrderMeal orderMeal) throws DaoException {
        Meal meal = orderMeal.getMeal();
        long mealId = meal.getId();
        String tempMealId = String.valueOf(mealId);
        int amount = orderMeal.getAmount();
        String tempAmount = String.valueOf(amount);
        executeUpdate(INSERT_ORDER_MEAL, tempMealId, tempAmount);
    }

    @Override
    protected String getTableName() {
        return "order_meal";
    }
}
