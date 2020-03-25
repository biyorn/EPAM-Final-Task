package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.entity.impl.OrderMeal;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderMealService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.web.command.constants.PagesConst.GO_TO_ORDERS;

public class OrdersListCommand implements Command {

    private OrderMealService service;

    public OrdersListCommand(OrderMealService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<OrderMeal> list = service.getNewOrders(OrderStatus.NEW);
        request.setAttribute("newOrders", list);
        return CommandResult.forward(GO_TO_ORDERS);
    }
}
