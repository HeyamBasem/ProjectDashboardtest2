package com.code.app.services;

import com.code.app.models.Dao.UserDAO;
import com.code.app.models.User;

import java.util.HashMap;
import java.util.List;

public class AuthService {
    private static UserDAO userDAO = UserDAO.getInstance();
    private User user = new User();
    public static boolean registeration(User newUser) {
        List<User> users = userDAO.read();
        for (User user: users){
            if(user.getUsername().equals(newUser.getUsername()))
                return false;
        }
        HashMap<String,Object> data = new HashMap<>();
        data.put("username",newUser.username);
        data.put("password",newUser.password);
        data.put("role","user");
        userDAO.create(data);
        return true;
    }

    public static boolean login(User loginUser){
        List<User> users = userDAO.read();
        for (User user: users){
            if(user.getUsername().equals(loginUser.getUsername()) && user.getPassword().equals(loginUser.getPassword()))
                return true;
        }
        return false;
    }

}
