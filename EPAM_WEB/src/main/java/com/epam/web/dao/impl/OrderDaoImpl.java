package com.epam.web.dao.impl;

import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.OrderDao;
import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.entity.impl.Order;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.DaoException;

import java.sql.Connection;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    private static final String INSERT_ORDER =
            "INSERT INTO orders(user_id, time, status, type, payment) VALUES(?,?,?,?,?)";
    private static final String UPDATE_STATUS = "UPDATE orders SET status = ? WHERE id = ?";
    private static final String UPDATE_REVIEW = "UPDATE orders SET review = ? WHERE id = ?";


    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void editStatus(Order order) throws DaoException {
        OrderStatus status = order.getStatus();
        String tempStatus = status.getTitle();
        long id = order.getId();
        String tempId = String.valueOf(id);
        executeUpdate(UPDATE_STATUS, tempStatus, tempId);
    }

    @Override
    public void leaveFeedback(Order order) throws DaoException {
        String review = order.getReview();
        long id = order.getId();
        String tempId = String.valueOf(id);
        executeUpdate(UPDATE_REVIEW, review, tempId);
    }

    @Override
    public void save(Order order) throws DaoException {
        User user = order.getUser();
        long userId = user.getId();
        String tempUserId = String.valueOf(userId);
        String time = order.getTime();
        OrderStatus status = order.getStatus();
        String tempStatus = status.getTitle();
        String preOrder = order.getPreOrder();
        String payment = order.getPayment();
        executeUpdate(INSERT_ORDER, tempUserId, time, tempStatus, preOrder, payment);
    }

    @Override
    protected String getTableName() {
        return "orders";
    }
}
