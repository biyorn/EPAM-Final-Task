package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.epam.web.command.constants.PagesConst.GO_TO_USERS;

public class UsersCommand implements Command {

    private UserService service;

    public UsersCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> users = service.getAllUsers();
        request.setAttribute("listUsers", users);
        return CommandResult.forward(GO_TO_USERS);
    }
}
