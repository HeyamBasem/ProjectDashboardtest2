package com.code.app.controllers;

import com.code.app.models.Dao.UserDAO;
import com.code.app.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = "/userHome")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("userServlet doGet");
        String action = request.getServletPath();
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insert(request, response);
                    break;
                case "/delete":
                    delete(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    update(request, response);
                    break;
                case "/list":
                    listUsers(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Authentication/login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }

    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        List< User > listUsers = UserDAO.getInstance().read();
        request.setAttribute("listUsers", listUsers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud/user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        User user = UserDAO.getInstance().getByUsername(username);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud/user-form.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);

    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HashMap<String,Object> data= new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String projectId = request.getParameter("projectId");

        data.put("username",username);
        data.put("password",password);
        data.put("projectId",projectId);

        UserDAO userDAO = UserDAO.getInstance();
        userDAO.create(data);
        response.sendRedirect("/list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HashMap<String,Object> data= new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String projectId = request.getParameter("projectId");

        data.put("username",username);
        data.put("password",password);
        data.put("projectId",projectId);

        UserDAO userDAO = UserDAO.getInstance();
        userDAO.update(id,data);
        response.sendRedirect("/list");


//        response.sendRedirect("list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDAO userDAO = UserDAO.getInstance();
        userDAO.delete(id);
        response.sendRedirect("/list");
    }

}
