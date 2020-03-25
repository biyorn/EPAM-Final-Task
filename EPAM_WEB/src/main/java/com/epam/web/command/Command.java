package com.epam.web.command;

import com.epam.web.controller.CommandResult;
import com.epam.web.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface {@code Command} is implement of
 * command pattern. It has one method <code>execute</code>
 * Every subclass should override this method.
 *
 * @author Pavel Orlovski
 * @see com.epam.web.command.Command
 * @since 1.1
 */
public interface Command {

    /**
     * Execute is a main and single method in this interface.
     * You should override it and implement for yourself.
     *
     * @param request
     * @param response
     * @return a new <code>CommandResult</code> which contain
     * next page for user.
     * @throws ServiceException this exception may happen
     * because of mistake at connecting in database.
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
