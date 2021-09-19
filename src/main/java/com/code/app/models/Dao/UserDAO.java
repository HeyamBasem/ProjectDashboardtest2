package com.code.app.models.Dao;

import com.code.app.DBUtil.DatabaseConnection;
import com.code.app.DBUtil.Request;
import com.code.app.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserDAO {
    Request reqBody;
    ObjectMapper objectMapper;

    private UserDAO() {
        System.out.println("user constructor");
        reqBody= new Request();
        objectMapper= new ObjectMapper();
    }

    public static UserDAO getInstance(){
        return new UserDAO();
    }


    public List<User> read() {
        reqBody.table="user";
        reqBody.operation="read";
        List<User> users = null;
        try
        {
            //connect to DB
            String result = DatabaseConnection.getInstance().requestRunner(reqBody);
            users= Arrays.asList(objectMapper.readValue(result, User[].class));

        }

        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return users;

    }

    public void create(HashMap<String ,Object> user) {
        System.out.println("inside create user");
        reqBody.table="user";
        reqBody.operation="create";
        reqBody.data= user;
        System.out.println(user.toString());
        DatabaseConnection.getInstance().requestRunner(reqBody);

    }

    public void update(int id, HashMap<String ,Object> user ){
        reqBody.table="user";
        reqBody.operation="update";
        user.put("id",id);
        reqBody.data= user;
        DatabaseConnection.getInstance().requestRunner(reqBody);
    }

    public void delete(int id) {
        reqBody.table="user";
        reqBody.operation="delete";
        reqBody.data.put("id",id);
        DatabaseConnection.getInstance().requestRunner(reqBody);

    }
    public List<User> getByProject(int id) {
        reqBody.table="user";
        reqBody.operation="getByProject";
        reqBody.data.put("id",id);
        List<User> users = null;
        try
        {
            //connect to DB
            String result = DatabaseConnection.getInstance().requestRunner(reqBody);
            users= Arrays.asList(objectMapper.readValue(result, User[].class));
        }

        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return users;

    }
    public User getByUsername(String username){
        List<User> users = read();
        for (User user: users){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

}
