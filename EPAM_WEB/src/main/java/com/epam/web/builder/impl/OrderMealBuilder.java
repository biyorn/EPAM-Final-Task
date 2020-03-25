package com.epam.web.builder.impl;

import com.epam.web.builder.Builder;
import com.epam.web.entity.impl.Meal;
import com.epam.web.entity.impl.Order;
import com.epam.web.entity.impl.OrderMeal;
import com.epam.web.entity.impl.User;
import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.reader.ConfigReader;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMealBuilder implements Builder<OrderMeal> {

    @Override
    public OrderMeal build(ResultSet resultSet) throws SQLException {
        Order order = createOrder(resultSet);
        Meal meal = createMeal(resultSet);
        int amount = resultSet.getInt(OrderMeal.AMOUNT);

        return new OrderMeal(order, meal, amount);
    }

    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order.Builder orderBuilder = Order.newBuilder();
        int orderId = resultSet.getInt("order_id");
        orderBuilder.buildId(orderId);

        User user = createUser(resultSet);
        orderBuilder.buildUser(user);

        String time = resultSet.getString(Order.TIME);
        orderBuilder.buildTime(time);

        String tempStatus = resultSet.getString(Order.STATUS);
        tempStatus = tempStatus.toUpperCase();
        OrderStatus status = OrderStatus.valueOf(tempStatus);
        orderBuilder.buildStatus(status);

        String review = resultSet.getString(Order.REVIEW);
        orderBuilder.buildReview(review);

        String tempPayment = resultSet.getString(Order.PAYMENT);
        boolean accountPayment = tempPayment.equalsIgnoreCase("account");
        orderBuilder.buildAccountPayment(accountPayment);

        return orderBuilder.build();
    }

    private Meal createMeal(ResultSet resultSet) throws SQLException {
        Meal.Builder mealBuilder = Meal.newBuilder();

        int mealId = resultSet.getInt("meal_id");
        mealBuilder.buildId(mealId);

        String path = ConfigReader.read("path");
        String image = path + resultSet.getString(Meal.IMAGE);
        mealBuilder.buildImage(image);

        String name = resultSet.getString(Meal.NAME);
        mealBuilder.buildName(name);

        String description = resultSet.getString(Meal.DESCRIPTION);
        mealBuilder.buildDescription(description);

        BigDecimal price = resultSet.getBigDecimal(Meal.PRICE);
        mealBuilder.buildPrice(price);

        return mealBuilder.build();
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User.Builder userBuilder = User.newBuilder();
        int userId = resultSet.getInt(Order.USER_ID);
        userBuilder.buildId(userId);

        String login = resultSet.getString(User.LOGIN);
        userBuilder.buildLogin(login);

        int points = resultSet.getInt(User.POINTS);
        userBuilder.buildPoints(points);

        return userBuilder.build();
    }
}
