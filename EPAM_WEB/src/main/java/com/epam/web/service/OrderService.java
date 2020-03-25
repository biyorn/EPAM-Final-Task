package com.epam.web.service;

import com.epam.web.dao.DaoFactory;
import com.epam.web.dao.impl.OrderDaoImpl;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.impl.Order;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class);

    private DaoFactory dao;

    public OrderService(DaoFactory dao) {
        this.dao = dao;
    }

    public void cancel(Order order) throws ServiceException {
        User user = order.getUser();
        try {
            try {
                dao.startTransaction();
                OrderDaoImpl orderDao = dao.createOrderDaoImpl();
                orderDao.editStatus(order);
                UserDaoImpl userDao = dao.createUserDaoImpl();
                userDao.editPoints(user);
                userDao.editBlock(user);
                dao.endTransaction();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void pay(Order order) throws ServiceException {
        try {
            OrderDaoImpl orderDao = dao.createOrderDaoImpl();
            orderDao.editStatus(order);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void leaveFeedback(Order order) throws ServiceException {
        try {
            OrderDaoImpl orderDao = dao.createOrderDaoImpl();
            orderDao.leaveFeedback(order);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
