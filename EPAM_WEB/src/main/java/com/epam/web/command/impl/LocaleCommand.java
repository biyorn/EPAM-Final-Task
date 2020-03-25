package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String language = request.getParameter("language");
        String country = request.getParameter("country");

        Locale locale = new Locale(language, country);
        HttpSession session = request.getSession();
        session.setAttribute("locale", locale);

        ResourceBundle bundle = ResourceBundle.getBundle("webContent", locale);
        request.setAttribute("webContent", bundle);

        String page = (String) session.getAttribute("currentPage");
        return CommandResult.redirect("controller?command=" + page);
    }
}
