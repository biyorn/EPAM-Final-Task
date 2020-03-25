package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.validation.LoginValidator;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.epam.web.command.constants.PagesConst.GO_TO_LOGIN;
import static com.epam.web.command.constants.PagesConst.MAIN_PAGE;

public class LoginCommand implements Command {

    private static final String ERROR = "error";
    private static final String USER = "user";

    private UserService service;
    private LoginValidator validator;

    public LoginCommand(UserService service, LoginValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(User.LOGIN);
        String tempPassword = request.getParameter(User.PASSWORD);
        if(!validator.verifyLogin(login) || !validator.verifyPassword(tempPassword)) {
            request.setAttribute(ERROR, "Incorrect login or password");
            return CommandResult.forward(GO_TO_LOGIN);
        }

        String password = DigestUtils.md5Hex(tempPassword);
        Optional<User> user = service.login(login, password);
        if(user.isPresent()) {
            User local = user.get();
            if(local.isBlocked()) {
                request.setAttribute(ERROR, "Your account is blocked");
                return CommandResult.forward(GO_TO_LOGIN);
            }
            HttpSession session = request.getSession();
            session.setAttribute(USER, local);
            return CommandResult.forward(MAIN_PAGE);
        } else {
            request.setAttribute(ERROR, "Incorrect login or password");
            return CommandResult.forward(GO_TO_LOGIN);
        }
    } 
}
