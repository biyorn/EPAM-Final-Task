package com.epam.web.dao;

import com.epam.web.entity.impl.Order;
import com.epam.web.exception.DaoException;

/**
 * Interface {@code OrderDao} is helper
 * for Dao pattern.
 *
 * @author Pavel Orlovski
 * @see com.epam.web.dao.OrderDao
 * @since 1.1
 */
public interface OrderDao {

    /**
     * This method replaces the status
     * and return nothing.
     *
     * @param order
     * @throws DaoException
     */
    void editStatus(Order order) throws DaoException;

    /**
     * This method fills feedback which
     * user left. It transmits data to database
     * and return nothing.
     *
     * @param order
     * @throws DaoException
     */
    void leaveFeedback(Order order) throws DaoException;
}
