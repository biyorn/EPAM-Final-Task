package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.Meal;
import com.epam.web.entity.impl.Order;
import com.epam.web.entity.impl.OrderMeal;
import com.epam.web.entity.impl.User;
import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderMealService;
import com.epam.web.validation.OrderValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

import static com.epam.web.command.constants.CommandConst.HISTORY;

public class OrderCommand implements Command {

    private static final String WITHOUT_TIME = "";
    private static final int PRE_ORDER_POINTS = 1;

    private OrderMealService service;
    private OrderValidator validator;

    public OrderCommand(OrderMealService service, OrderValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String tempMealId = request.getParameter(Meal.ID);
        String tempPrice = request.getParameter(Meal.PRICE);
        String time = request.getParameter(Order.TIME);
        String tempPayment = request.getParameter(Order.PAYMENT);
        String tempAmount = request.getParameter("amount");

        checkParameters(tempMealId, tempPrice, time, tempPayment, tempAmount);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Meal.Builder mealBuilder = Meal.newBuilder();
        long mealId = Long.parseLong(tempMealId);
        mealBuilder.buildId(mealId);
        BigDecimal price = new BigDecimal(tempPrice);
        mealBuilder.buildPrice(price);
        Meal meal = mealBuilder.build();

        Order.Builder builder = Order.newBuilder();
        builder.buildTime(time);
        boolean accountPayment = tempPayment.equals("account");
        builder.buildAccountPayment(accountPayment);

        OrderStatus status = determineTypeOrder(tempPayment);
        builder.buildStatus(status);

        int amount = Integer.parseInt(tempAmount);

        if (accountPayment) {
            Optional<User> tempUser = payAccount(user, meal, amount);
            if (tempUser.isPresent()) {
                user = tempUser.get();
            } else {
                request.setAttribute("error", "Not enough money");
                return CommandResult.redirect("controller?command=meal&id=" + meal.getId());
            }
        }

        boolean preOrder = !time.equals(WITHOUT_TIME);
        builder.buildPreOrder(preOrder);
        if (preOrder) {
            int points = user.getPoints() + PRE_ORDER_POINTS;
            user.setPoints(points);
        }

        builder.buildUser(user);
        Order order = builder.build();
        OrderMeal orderMeal = new OrderMeal(order, meal, amount);

        service.makeOrder(orderMeal);
        session.setAttribute("user", user);
        return CommandResult.redirect("controller?command=" + HISTORY);
    }

    private Optional<User> payAccount(User user, Meal meal, int amount) {
        double price = meal.getPrice().doubleValue();
        BigDecimal total = BigDecimal.valueOf(amount * price);
        BigDecimal balance = user.getBalance();
        if (balance.compareTo(total) >= 0) {
            balance = balance.subtract(total);
            user.setBalance(balance);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    private OrderStatus determineTypeOrder(String payment) {
        return payment.equals("cash") ? OrderStatus.NEW : OrderStatus.PAID;
    }

    private void checkParameters(String tempMealId, String price, String time, String payment, String amount) {
        if (!validator.verifyId(tempMealId)
                || !validator.verifyPrice(price)
                || !validator.verifyTime(time)
                || !validator.verifyPayment(payment)
                || !validator.verifyAmount(amount)) {
            throw new IllegalArgumentException("Wrong parameters, try again please!");
        }
    }
}
