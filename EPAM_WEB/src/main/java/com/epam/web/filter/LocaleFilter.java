package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute("locale");

        if(locale == null) {
            locale = request.getLocale();
        }
        ResourceBundle bundle = ResourceBundle.getBundle("webContent", locale);
        request.setAttribute("webContent", bundle);

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
