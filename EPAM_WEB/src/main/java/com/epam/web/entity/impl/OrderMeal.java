package com.epam.web.entity.impl;

import com.epam.web.entity.Identifable;

import java.io.Serializable;

public class OrderMeal implements Identifable, Serializable {

    public static final String AMOUNT = "amount";

    private Order order;
    private Meal meal;
    private int amount;

    public OrderMeal(Order order, Meal meal, int amount) {
        this.order = order;
        this.meal = meal;
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public Meal getMeal() {
        return meal;
    }

    public int getAmount() {
        return amount;
    }
}
