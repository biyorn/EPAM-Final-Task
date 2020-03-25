package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.Order;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.OrderService;
import com.epam.web.validation.FeedbackValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.web.command.constants.CommandConst.HISTORY;

public class FeedbackCommand implements Command {

    private OrderService service;
    private FeedbackValidator validator;

    public FeedbackCommand(OrderService service, FeedbackValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String review = request.getParameter(Order.REVIEW);
        if(!validator.verifyReview(review)) {
            throw new IllegalArgumentException("Something went wrong");
        }

        String tempId = request.getParameter(Order.ID);
        long id = Long.valueOf(tempId);
        Order.Builder builder = Order.newBuilder();
        builder.buildReview(review);
        builder.buildId(id);
        Order order = builder.build();

        service.leaveFeedback(order);

        return CommandResult.redirect("controller?command=" + HISTORY);
    }
}
