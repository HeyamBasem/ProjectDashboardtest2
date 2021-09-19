package com.code.app.models.Dao;

import com.code.app.DBUtil.DatabaseConnection;
import com.code.app.DBUtil.Request;
import com.code.app.models.Project;
import com.code.app.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProjectDAO {
    Request reqBody;
    ObjectMapper objectMapper;

    private ProjectDAO() {
        reqBody= new Request();
        objectMapper= new ObjectMapper();
    }

    public static ProjectDAO getInstance(){
        return new ProjectDAO();
    }


    public List<Project> read() {
        reqBody.table="project";
        reqBody.operation="read";
        List<Project> projects = null;
        try
        {
            //connect to DB
            String result = DatabaseConnection.getInstance().requestRunner(reqBody);
            projects= Arrays.asList(objectMapper.readValue(result, Project[].class));

        }

        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return projects;

    }

    public void create(HashMap<String ,Object> project) {

        reqBody.table="project";
        reqBody.operation="create";
        reqBody.data= project;
        DatabaseConnection.getInstance().requestRunner(reqBody);

    }

    public void update(int id, HashMap<String ,Object> project ){
        reqBody.table="project";
        reqBody.operation="update";
        project.put("id",id);
        reqBody.data= project;
        DatabaseConnection.getInstance().requestRunner(reqBody);
    }

    public void delete(int id) {
        reqBody.table="project";
        reqBody.operation="delete";
        reqBody.data.put("id",id);
        DatabaseConnection.getInstance().requestRunner(reqBody);

    }
    public Project getById(int id){
        List<Project> projects = read();
        for (Project project: projects){
            if(project.getId()==id)
                return project;
        }
        return null;
    }


}
