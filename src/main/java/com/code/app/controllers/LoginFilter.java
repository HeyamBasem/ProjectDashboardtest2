package com.code.app.controllers;

import com.code.app.models.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        System.out.println("inside doFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";

        boolean loggedIn = session != null && session.getAttribute("loggedIn") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        System.out.println("loggedIn:"+loggedIn+" loginRequest:"+loginRequest);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response);
        } else {
            System.out.println("loginfilter else loginURI:"+loginURI);
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {

    }
}