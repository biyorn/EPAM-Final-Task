package com.epam.web.entity.impl;

import com.epam.web.entity.Identifable;

import java.io.Serializable;
import java.math.BigDecimal;

public class Meal implements Identifable, Serializable {

    public static final String ID = "id";
    public static final String IMAGE = "image";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";

    private long id;
    private String image;
    private String name;
    private String description;
    private BigDecimal price;

    private Meal() {}

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public static Builder newBuilder() {
        return new Meal().new Builder();
    }

    public class Builder {

        public Builder buildId(long id) {
            Meal.this.id = id;
            return this;
        }

        public Builder buildImage(String image) {
            Meal.this.image = image;
            return this;
        }

        public Builder buildName(String name) {
            Meal.this.name = name;
            return this;
        }

        public Builder buildDescription(String description) {
            Meal.this.description = description;
            return this;
        }

        public Builder buildPrice(BigDecimal price) {
            Meal.this.price = price;
            return this;
        }

        public Meal build() {
            return Meal.this;
        }
    }
}
