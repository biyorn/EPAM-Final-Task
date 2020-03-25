package com.epam.web.dao.impl;

import com.epam.web.builder.impl.MealBuilder;
import com.epam.web.dao.AbstractDao;
import com.epam.web.dao.MealDao;
import com.epam.web.entity.impl.Meal;
import com.epam.web.exception.DaoException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MealDaoImpl extends AbstractDao<Meal> implements MealDao {

    private static final String INSERT_MEAL =
            "INSERT INTO meals(image, name, description, price) VALUES(?,?,?,?)";

    private static final String SELECT_FIVE_MEAL =
            "SELECT * FROM meals LIMIT 4 OFFSET ";

    public MealDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Meal> findFourMeals(int page) throws DaoException {
        return executeQuery(SELECT_FIVE_MEAL + page, new MealBuilder());
    }

    @Override
    public void save(Meal meal) throws DaoException {
        String image = meal.getImage();
        String name = meal.getName();
        String description = meal.getDescription();
        BigDecimal price = meal.getPrice();
        String tempPrice = String.valueOf(price);
        executeUpdate(INSERT_MEAL, image, name, description, tempPrice);
    }

    @Override
    protected String getTableName() {
        return "meals";
    }
}
