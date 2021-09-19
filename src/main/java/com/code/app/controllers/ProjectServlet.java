package com.code.app.controllers;

import com.code.app.models.Dao.ProjectDAO;
import com.code.app.models.Dao.UserDAO;
import com.code.app.models.Project;
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

@WebServlet(urlPatterns = "/projectHome")
public class ProjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            case "/projectUsers":
                projectUsers(request, response);
                break;
            case "/list":
                listProjects(request, response);
                break;
            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Authentication/login.jsp");
                dispatcher.forward(request, response);
                break;
        }

    }

    private void listProjects(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        List< Project > listProjects = ProjectDAO.getInstance().read();
        request.setAttribute("listProjects", listProjects);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud/project-list.jsp");
        dispatcher.forward(request, response);
    }
    private void projectUsers(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        List< User > projectUsers = UserDAO.getInstance().getByProject(id);
        request.setAttribute("projectUsers", projectUsers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud/project-list.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud/project_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Project project = ProjectDAO.getInstance().getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/crud/project_form.jsp");
        request.setAttribute("project", project);
        dispatcher.forward(request, response);

    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HashMap<String,Object> data= new HashMap<>();
        String name = request.getParameter("name");
        String department = request.getParameter("department");

        data.put("name",name);
        data.put("department",department);

        ProjectDAO projectDAO = ProjectDAO.getInstance();
        projectDAO.create(data);
        response.sendRedirect(request.getContextPath()+"/list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HashMap<String,Object> data= new HashMap<>();
        String name = request.getParameter("name");
        String department = request.getParameter("department");

        data.put("name",name);
        data.put("department",department);

        ProjectDAO projectDAO = ProjectDAO.getInstance();
        projectDAO.update(id,data);
        response.sendRedirect(request.getContextPath()+"/list");


//        response.sendRedirect("list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProjectDAO projectDAO = ProjectDAO.getInstance();
        projectDAO.delete(id);
        response.sendRedirect(request.getContextPath()+"/list");
    }


}
