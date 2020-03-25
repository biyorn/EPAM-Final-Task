package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.web.command.constants.PagesConst.START_PAGE;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        session.invalidate();
        return CommandResult.forward(START_PAGE);
    }
}
