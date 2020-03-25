package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.Meal;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.MealService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.epam.web.command.constants.PagesConst.GO_TO_MEAL;

public class MealCommand implements Command {

    private MealService service;

    public MealCommand(MealService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String tempId = request.getParameter(Meal.ID);
        Long id = Long.valueOf(tempId);
        Optional<Meal> meal = service.getById(id);
        if(meal.isPresent()) {
            request.setAttribute("meal", meal.get());
            return CommandResult.forward(GO_TO_MEAL);
        } else {
            throw new IllegalArgumentException("Unknown meal");
        }
    }
}
