package com.epam.web.service;

import com.epam.web.dao.DaoFactory;
import com.epam.web.dao.impl.OrderDaoImpl;
import com.epam.web.dao.impl.OrderMealDaoImpl;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.entity.impl.Order;
import com.epam.web.entity.impl.OrderMeal;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class OrderMealService {

    private static final Logger LOGGER = Logger.getLogger(OrderMealService.class);

    private DaoFactory dao;

    public OrderMealService(DaoFactory dao) {
        this.dao = dao;
    }

    public void makeOrder(OrderMeal orderMeal) throws ServiceException {
        Order order = orderMeal.getOrder();
        User user = order.getUser();
        try {
            try {
                dao.startTransaction();
                OrderDaoImpl orderDao = dao.createOrderDaoImpl();
                orderDao.save(order);
                OrderMealDaoImpl orderMealDao = dao.createOrderMealDaoImpl();
                orderMealDao.save(orderMeal);
                UserDaoImpl userDao = dao.createUserDaoImpl();
                userDao.editPoints(user);
                userDao.editBalance(user);
                userDao.editBlock(user);
                dao.endTransaction();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<OrderMeal> getUserOrdersById(Long id) throws ServiceException {
        try {
            OrderMealDaoImpl orderMealDao = dao.createOrderMealDaoImpl();
            return orderMealDao.findUserOrdersById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<OrderMeal> getNewOrders(OrderStatus status) throws ServiceException {
        try {
            OrderMealDaoImpl orderMealDao = dao.createOrderMealDaoImpl();
            return orderMealDao.findOrdersByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
