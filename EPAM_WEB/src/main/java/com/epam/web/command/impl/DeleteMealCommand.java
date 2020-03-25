package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.Meal;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.MealService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.epam.web.command.constants.CommandConst.MENU;

public class DeleteMealCommand implements Command {

    private MealService service;

    public DeleteMealCommand(MealService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String tempId = request.getParameter(Meal.ID);
        Long id = Long.valueOf(tempId);
        service.removeById(id);

        return CommandResult.redirect("controller?command=" + MENU);
    }
}
