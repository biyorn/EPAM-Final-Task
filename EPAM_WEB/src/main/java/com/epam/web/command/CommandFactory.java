package com.epam.web.command;

import com.epam.web.command.impl.*;
import com.epam.web.dao.DaoFactory;
import com.epam.web.service.MealService;
import com.epam.web.service.OrderMealService;
import com.epam.web.service.OrderService;
import com.epam.web.service.UserService;
import com.epam.web.validation.*;

import static com.epam.web.command.constants.CommandConst.*;
import static com.epam.web.command.constants.PagesConst.GO_TO_LOGIN;
import static com.epam.web.command.constants.PagesConst.MAIN_PAGE;

public class CommandFactory {

    private DaoFactory dao;

    public CommandFactory(DaoFactory dao) {
        this.dao = dao;
    }

    public Command create(String command) {
        switch (command) {
            case SIGN_IN:
                return new ShowPageCommand(GO_TO_LOGIN);
            case MAIN:
                return new ShowPageCommand(MAIN_PAGE);
            case LOGIN:
                return new LoginCommand(new UserService(dao), new LoginValidator());
            case LOGOUT:
                return new LogoutCommand();
            case MENU:
                return new MenuCommand(new MealService(dao), new PageValidator());
            case USERS:
                return new UsersCommand(new UserService(dao));
            case HISTORY:
                return new HistoryCommand(new OrderMealService(dao));
            case NEW_MEAL:
                return new NewMealCommand(new MealService(dao), new NewMealValidator());
            case DELETE_MEAL:
                return new DeleteMealCommand(new MealService(dao));
            case LOCALE:
                return new LocaleCommand();
            case MEAL:
                return new MealCommand(new MealService(dao));
            case ORDER:
                return new OrderCommand(new OrderMealService(dao), new OrderValidator());
            case ORDERS_LIST:
                return new OrdersListCommand(new OrderMealService(dao));
            case EDIT:
                return new EditCommand(new UserService(dao), new EditValidator());
            case BALANCE:
                return new BalanceCommand(new UserService(dao), new CardValidator());
            case USER_CANCEL:
                return new UserCancelCommand(new OrderService(dao));
            case MANAGER_PAID:
                return new ManagerPaidCommand(new OrderService(dao));
            case MANAGER_CANCEL:
                return new ManagerCancelCommand(new OrderService(dao));
            case FEEDBACK:
                return new FeedbackCommand(new OrderService(dao), new FeedbackValidator());
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }
}
