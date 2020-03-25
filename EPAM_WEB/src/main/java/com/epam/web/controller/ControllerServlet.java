package com.epam.web.controller;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.dao.DaoFactory;
import com.epam.web.jdbc.ConnectionPool;
import com.epam.web.jdbc.ProxyConnection;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.web.command.constants.CommandConst.COMMAND;
import static com.epam.web.command.constants.PagesConst.ERROR_PAGE;

public class ControllerServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ControllerServlet.class);
    private static final String CURRENT_PAGE = "currentPage";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter(COMMAND);
        String page;

        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.getInstance().getConnection())) {
            DaoFactory factory = new DaoFactory(connection);
            CommandFactory commandFactory = new CommandFactory(factory);
            Command action = commandFactory.create(command);

            CommandResult result = action.execute(request, response);

            page = result.getPage();
            HttpSession session = request.getSession();
            session.setAttribute(CURRENT_PAGE, command);

            if (result.isForward()) {
                dispatch(request, response, page);
            } else {
                redirect(response, page);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            request.setAttribute("error", e.getMessage());
            dispatch(request, response, ERROR_PAGE);
        }
    }

    private void redirect(HttpServletResponse response, String page) throws IOException {
        response.sendRedirect(page);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.close();
    }
}
