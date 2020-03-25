package com.epam.web.entity.impl;

import com.epam.web.entity.Identifable;
import com.epam.web.entity.enums.OrderStatus;

import java.io.Serializable;

public class Order implements Identifable, Serializable {

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String TIME = "time";
    public static final String STATUS = "status";
    public static final String PRE_ORDER = "type";
    public static final String PAYMENT = "payment";
    public static final String REVIEW = "review";

    private long id;
    private User user;
    private String time;
    private OrderStatus status;
    private boolean preOrder;
    private boolean accountPayment;
    private String review;

    private Order() {}

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean isPreOrder() {
        return preOrder;
    }

    public String getPreOrder() {
        return preOrder ? "pre order" : "order";
    }

    public boolean isAccountPayment() {
        return accountPayment;
    }

    public String getPayment() {
        return accountPayment ? "account" : "cash";
    }

    public String getReview() {
        return review;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public static Builder newBuilder() {
        return new Order().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder buildId(long id) {
            Order.this.id = id;
            return this;
        }

        public Builder buildUser(User user) {
            Order.this.user = user;
            return this;
        }

        public Builder buildTime(String time) {
            Order.this.time = time;
            return this;
        }

        public Builder buildStatus(OrderStatus status) {
            Order.this.status = status;
            return this;
        }

        public Builder buildPreOrder(boolean preOrder) {
            Order.this.preOrder = preOrder;
            return this;
        }

        public Builder buildAccountPayment(boolean accountPayment) {
            Order.this.accountPayment = accountPayment;
            return this;
        }


        public Builder buildReview(String review) {
            Order.this.review = review;
            return this;
        }

        public Order build() {
            return Order.this;
        }
    }
}
