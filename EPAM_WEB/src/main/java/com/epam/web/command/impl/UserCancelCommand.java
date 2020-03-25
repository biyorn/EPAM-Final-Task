package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.Order;
import com.epam.web.entity.impl.User;
import com.epam.web.entity.enums.OrderStatus;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.web.command.constants.CommandConst.HISTORY;

public class UserCancelCommand implements Command {

    private OrderService service;

    public UserCancelCommand(OrderService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int points = user.getPoints() - 1;
        boolean block = points == 0;
        user.setBlocked(block);
        user.setPoints(points);

        Order.Builder builder = Order.newBuilder();
        String tempId = request.getParameter(Order.ID);
        long id = Long.parseLong(tempId);
        builder.buildId(id);
        builder.buildUser(user);
        builder.buildStatus(OrderStatus.CANCEL);
        Order order = builder.build();

        service.cancel(order);

        return CommandResult.redirect("controller?command=" + HISTORY);
    }
}
