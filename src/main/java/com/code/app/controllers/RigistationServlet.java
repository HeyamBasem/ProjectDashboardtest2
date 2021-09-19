package com.code.app.controllers;

import com.code.app.models.User;
import com.code.app.services.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RigistationServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("registration dopost");
        register(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("reg do Get before call");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Authentication/registration.jsp");
        dispatcher.forward(request, response);
        System.out.println("reg do Get After call");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("registration register");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        if(password!=password2){
            System.out.println("registration register password not matched");
            request.setAttribute("SignupErrorMessage","password dosn't match password2");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Authentication/registration.jsp");
            dispatcher.forward(request, response);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        try {
            System.out.println("registration before call registeration for auth service ");
            boolean result = AuthService.registeration(user);
            System.out.println("registration after call registeration for auth service :"+result);

            if (result == true) {
                System.out.println("-reg if reg succesfully-----------------------:"+(result==true));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Authentication/login.jsp");
                dispatcher.forward(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}