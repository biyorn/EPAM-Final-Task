package com.epam.web.builder.impl;

import com.epam.web.builder.Builder;
import com.epam.web.entity.impl.Meal;
import com.epam.web.reader.ConfigReader;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealBuilder implements Builder<Meal> {

    @Override
    public Meal build(ResultSet resultSet) throws SQLException {
        Meal.Builder builder = Meal.newBuilder();
        String image = ConfigReader.read("path");

        int id = resultSet.getInt(Meal.ID);
        builder.buildId(id);

        image += resultSet.getString(Meal.IMAGE);
        builder.buildImage(image);

        String name = resultSet.getString(Meal.NAME);
        builder.buildName(name);

        String description = resultSet.getString(Meal.DESCRIPTION);
        builder.buildDescription(description);

        BigDecimal price = resultSet.getBigDecimal(Meal.PRICE);
        builder.buildPrice(price);

        return builder.build();
    }
}
