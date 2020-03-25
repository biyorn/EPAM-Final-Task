package com.epam.web.dao;

import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.entity.impl.OrderMeal;
import com.epam.web.exception.DaoException;

import java.util.List;

/**
 * Interface {@code OrderMealDao} is a helper
 * for work with OrderMeal table in database.
 *
 * @author Pavel Orlovski
 * @see com.epam.web.dao.OrderMealDao
 * @since 1.1
 */
public interface OrderMealDao {

    /**
     * This method is looking for user orders
     * by user id. It transmits id to database
     * and return List<code>OrderMeal</code>
     *
     * @param id
     * @return a new List<code>OrderMeal</code>
     * @throws DaoException
     */
    List<OrderMeal> findUserOrdersById(Long id) throws DaoException;

    /**
     * This method is looking for orders by
     * status. You should transmit status and
     * you will get List<code>OrderMeal</code>
     * which contain objects with your status.
     *
     * @param status
     * @return a new List<code>OrderMeal</code>
     * @throws DaoException
     */
    List<OrderMeal> findOrdersByStatus(OrderStatus status) throws DaoException;
}
