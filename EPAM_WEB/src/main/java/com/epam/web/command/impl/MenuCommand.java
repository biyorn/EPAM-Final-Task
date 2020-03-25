package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.Meal;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.MealService;
import com.epam.web.validation.PageValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.web.command.constants.PagesConst.GO_TO_MENU;

public class MenuCommand implements Command {

    private static final int AMOUNT_MEALS_PAGE = 4;

    private MealService service;
    private PageValidator validator;

    public MenuCommand(MealService service, PageValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = request.getParameter("page");
        checkPageParameter(page);

        int number = getPage(page);

        List<Meal> list = service.getAllMenu();
        List<Meal> meals = service.getMeals(number * AMOUNT_MEALS_PAGE);

        request.setAttribute("amount", list.size());
        request.setAttribute("menu", meals);
        request.setAttribute("page", number + 1);

        return CommandResult.forward(GO_TO_MENU);
    }

    private int getPage(String page) {
        int number = 0;
        if(page != null) {
            number = Integer.parseInt(page) - 1;
        }
        return number;
    }

    private void checkPageParameter(String page) {
        if (page != null && !validator.verifyPage(page)) {
            throw new IllegalArgumentException("Unknown page");
        }
    }
}
