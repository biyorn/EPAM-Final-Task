package com.epam.web.builder;

import com.epam.web.builder.impl.MealBuilder;
import com.epam.web.builder.impl.OrderMealBuilder;
import com.epam.web.builder.impl.UserBuilder;

public class BuilderFactory {

    public static Builder get(String table) {
        switch (table) {
            case "users":
                return new UserBuilder();
            case "meals":
                return new MealBuilder();
            case "order_meal":
                return new OrderMealBuilder();
                default:
                    throw new IllegalArgumentException("Unknown table");
        }
    }
}
