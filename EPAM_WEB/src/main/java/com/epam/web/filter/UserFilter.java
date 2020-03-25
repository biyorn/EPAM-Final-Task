package com.epam.web.filter;

import com.epam.web.entity.impl.User;
import com.epam.web.entity.enums.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.epam.web.command.constants.CommandConst.*;

public class UserFilter implements Filter {

    private static final List<String> accessCommandsAll =
            Arrays.asList(SIGN_IN, LOGIN, LOCALE, MAIN);
    private static final List<String> adminCommands =
            Arrays.asList(MAIN, MENU, USERS, LOCALE, LOGOUT, NEW_MEAL, DELETE_MEAL, EDIT);
    private static final List<String> managerCommands =
            Arrays.asList(MAIN, LOCALE, ORDERS_LIST, MANAGER_PAID, MANAGER_CANCEL, LOGOUT);
    private static final List<String> userCommands =
            Arrays.asList(MAIN, MENU, MEAL, LOGOUT, LOCALE, ORDER, HISTORY, BALANCE, USER_CANCEL, FEEDBACK);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        String command = request.getParameter("command");

        if (user == null) {
            checkCommandUser(accessCommandsAll, command, filterChain, SIGN_IN, request, response);
        } else {
            UserRole role = user.getRole();

            if(role == UserRole.USER) {
                checkCommandUser(userCommands, command, filterChain, MAIN, request, response);
            } else if(role == UserRole.ADMIN) {
                checkCommandUser(adminCommands, command, filterChain, MAIN, request, response);
            } else if(role == UserRole.MANAGER) {
                checkCommandUser(managerCommands, command, filterChain, MAIN, request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }

    private void checkCommandUser(List<String> commandsList, String command, FilterChain filterChain, String page,
                                  HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(commandsList.contains(command)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("controller?command=" + page);
        }
    }
}
