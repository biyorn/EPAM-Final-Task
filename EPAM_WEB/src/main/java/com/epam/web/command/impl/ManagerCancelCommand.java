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

public class ManagerCancelCommand implements Command {

    private OrderService service;

    public ManagerCancelCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User.Builder userBuilder = User.newBuilder();
        String tempUserId = request.getParameter("user_id");
        long userId = Long.parseLong(tempUserId);
        userBuilder.buildId(userId);
        String tempPoints = request.getParameter("points");
        int points = Integer.parseInt(tempPoints) - 1;
        userBuilder.buildPoints(points);
        String tempBlock = request.getParameter("block");
        boolean block = Boolean.valueOf(tempBlock);
        if(points <= 0 && !block) {
            block = true;
        }
        userBuilder.buildBlocked(block);
        User user = userBuilder.build();

        Order.Builder orderBuilder = Order.newBuilder();
        orderBuilder.buildUser(user);
        String tempOrderId = request.getParameter("order_id");
        long orderId = Long.parseLong(tempOrderId);
        orderBuilder.buildId(orderId);
        orderBuilder.buildStatus(OrderStatus.CANCEL);
        Order order = orderBuilder.build();

        service.cancel(order);

        return CommandResult.redirect("controller?command=" + ORDERS_LIST);
    }
}
