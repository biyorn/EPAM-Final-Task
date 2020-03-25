package com.epam.web.dao;

import com.epam.web.entity.impl.Meal;
import com.epam.web.exception.DaoException;

import java.util.List;

/**
 * Interface {@code MealDao} is designed for help
 * Dao.
 *
 *
 * @author Pavel Orlovski
 * @see com.epam.web.dao.MealDao
 * @since 1.1
 */
public interface MealDao {

    /**
     * This method is looking for four meal
     * and return them such as List.
     * It make request to database and create
     * objects.
     *
     * @param page
     * @return new List<code>Meal</> which contain
     * four meals from database.
     * @throws DaoException
     */
    List<Meal> findFourMeals(int page) throws DaoException;
}
