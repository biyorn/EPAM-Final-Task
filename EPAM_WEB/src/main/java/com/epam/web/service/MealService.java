package com.epam.web.service;

import com.epam.web.dao.DaoFactory;
import com.epam.web.dao.impl.MealDaoImpl;
import com.epam.web.entity.impl.Meal;
import com.epam.web.exception.DaoException;
import com.epam.web.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class MealService {

    private DaoFactory dao;

    public MealService(DaoFactory dao) {
        this.dao = dao;
    }

    public List<Meal> getAllMenu() throws ServiceException {
        try {
            MealDaoImpl mealDao = dao.createMealDaoImpl();
            return mealDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Meal> getMeals(int page) throws ServiceException {
        try {
            MealDaoImpl mealDao = dao.createMealDaoImpl();
            return mealDao.findFourMeals(page);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void saveMeal(Meal meal) throws ServiceException {
        try {
            MealDaoImpl mealDao = dao.createMealDaoImpl();
            mealDao.save(meal);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Meal> getById(Long id) throws ServiceException {
        try {
            MealDaoImpl mealDao = dao.createMealDaoImpl();
            return mealDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void removeById(Long id) throws ServiceException {
        try {
            MealDaoImpl mealDao = dao.createMealDaoImpl();
            mealDao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
