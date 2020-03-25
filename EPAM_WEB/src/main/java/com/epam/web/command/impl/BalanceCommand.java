package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.controller.CommandResult;
import com.epam.web.entity.impl.User;
import com.epam.web.exception.ServiceException;
import com.epam.web.service.UserService;
import com.epam.web.validation.CardValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

import static com.epam.web.command.constants.CommandConst.MENU;

public class BalanceCommand implements Command {

    private UserService service;
    private CardValidator validator;

    public BalanceCommand(UserService service, CardValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String card = request.getParameter("card");
        String mm = request.getParameter("mm");
        String yy = request.getParameter("yy");
        String cvs = request.getParameter("cvs");
        String amount = request.getParameter("amount");
        checkCardParameters(card, mm, yy, cvs, amount);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        BigDecimal balance = new BigDecimal(amount);
        balance = balance.add(user.getBalance());
        user.setBalance(balance);

        service.editUser(user);

        session.setAttribute("user", user);

        return CommandResult.redirect("controller?command=" + MENU);
    }

    private void checkCardParameters(String card, String mm, String yy, String cvs, String amount) {
        if(!validator.verifyCardNumber(card)
                || !validator.verifyMonth(mm)
                || !validator.verifyYear(yy)
                || !validator.verifyCvs(cvs)
                || !validator.verifyAmount(amount)) {
            throw new IllegalArgumentException("Wrong parameters!");
        }
    }
}
