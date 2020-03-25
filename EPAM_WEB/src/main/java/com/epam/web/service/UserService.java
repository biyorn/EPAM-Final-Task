package com.epam.web.service;

import com.epam.web.dao.DaoFactory;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.impl.UserDaoImpl;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private DaoFactory dao;

    public UserService(DaoFactory dao) {
        this.dao = dao;
    }


    public List<User> getAllUsers() throws ServiceException {
        try {
            UserDaoImpl userDao = dao.createUserDaoImpl();
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            UserDao userDao = dao.createUserDaoImpl();
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void editUser(User user) throws ServiceException {
        try {
            try {
                dao.startTransaction();
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
}
