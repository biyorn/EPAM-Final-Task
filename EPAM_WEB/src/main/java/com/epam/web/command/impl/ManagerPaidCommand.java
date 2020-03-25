package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.entity.impl.Order;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.web.command.constants.CommandConst.ORDERS_LIST;

public class ManagerPaidCommand implements Command {

    private OrderService service;

    public ManagerPaidCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Order.Builder orderBuilder = Order.newBuilder();
        String tempId = request.getParameter("id");
        long id = Long.parseLong(tempId);
        orderBuilder.buildId(id);
        orderBuilder.buildStatus(OrderStatus.PAID);
        Order order = orderBuilder.build();

        service.pay(order);

        return CommandResult.redirect("controller?command=" + ORDERS_LIST);
    }
}
