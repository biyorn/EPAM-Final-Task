package com.epam.web.dao;

import com.epam.web.entity.impl.User;
import com.epam.web.exception.DaoException;

import java.util.Optional;

/**
 * Interface {@code UserDao} is a helper
 * for work with user table. This interface
 * contains methods which will help receive
 * required data or update old.
 *
 * @author Pavel Orlovski
 * @see com.epam.web.dao.UserDao
 * @since 1.1
 */
public interface UserDao {

    /**
     * This method is designed for
     * search user by login and password
     * in database. If user was not found
     * then method return empty Optional.
     * If not then method return Optional
     * with User.
     *
     * @param login
     * @param password
     * @return Optional<code>User</code>
     * @throws DaoException
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * This method is designed to change
     * user points. It just transmits data
     * to database and return nothing.
     *
     * @param user
     * @throws DaoException
     */
    void editPoints(User user) throws DaoException;

    /**
     * This method is designed to change
     * user balance.
     *
     * @param user
     * @throws DaoException
     */
    void editBalance(User user) throws DaoException;

    /**
     * This method is designed to change block
     * of user.
     *
     * @param user
     * @throws DaoException
     */
    void editBlock(User user) throws DaoException;
}
