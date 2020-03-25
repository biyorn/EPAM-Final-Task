package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.OrderMeal;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderMealService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.web.command.constants.PagesConst.GO_TO_HISTORY;

public class HistoryCommand implements Command {

    private OrderMealService service;

    public HistoryCommand(OrderMealService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getId();
        List<OrderMeal> orderMeals = service.getUserOrdersById(userId);
        request.setAttribute("userOrders", orderMeals);
        return CommandResult.forward(GO_TO_HISTORY);
    }
}
