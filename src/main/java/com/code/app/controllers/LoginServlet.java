package com.code.app.controllers;

import com.code.app.models.User;
import com.code.app.services.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("login servlet post");
        loginService(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("login servlet get");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Authentication/login.jsp");
        dispatcher.forward(request, response);
    }

    private void loginService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println("inside loginService");

        try {
            System.out.println("before call login from authservice");
            boolean result = AuthService.login(user);
            System.out.println("after call login from authservice" +result);
            if (result == true) {
                System.out.println("login-------------------------");
                HttpSession session = request.getSession(true);
                session.setAttribute("loggedIn",true);
                response.sendRedirect(request.getContextPath()+"/userHome");
//                response.sendRedirect("/userHome");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
