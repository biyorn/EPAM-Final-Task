package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.validation.EditValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

import static com.epam.web.command.constants.CommandConst.USERS;

public class EditCommand implements Command {

    private static final String UNBLOCK = "unblock";

    private UserService service;
    private EditValidator validator;

    public EditCommand(UserService service, EditValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String tempId = request.getParameter(User.ID);
        String tempBalance = request.getParameter(User.BALANCE);
        String tempPoints = request.getParameter(User.POINTS);
        checkParameters(tempId, tempBalance, tempPoints);

        User.Builder builder = User.newBuilder();

        int userId = Integer.parseInt(tempId);
        builder.buildId(userId);

        BigDecimal balance = new BigDecimal(tempBalance);
        builder.buildBalance(balance);

        int points = Integer.parseInt(tempPoints);
        builder.buildPoints(points);

        String block = request.getParameter(User.BLOCKED);
        boolean blocked = !block.equals(UNBLOCK);
        builder.buildBlocked(blocked);

        User user = builder.build();

        service.editUser(user);

        return CommandResult.redirect("controller?command=" + USERS);
    }

    private void checkParameters(String id, String balance, String points) {
        if(!validator.verifyId(id)
                || !validator.verifyBalance(balance)
                || !validator.verifyPoints(points)) {
            throw new IllegalArgumentException("Wrong parameters");
        }
    }
}
