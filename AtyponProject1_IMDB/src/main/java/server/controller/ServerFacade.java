package server.controller;

import  operations.Constants;
import  operations.Response;
import  operations.ResponseStatus;
import   server.controller.database.DataBase;
import   server.controller.database.DataBaseFactory;
import   server.model.content.BaseContent;
import   server.model.content.PublicationContent;
import   server.model.user.*;
import operations.Response;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServerFacade {
    private DataBase<BaseUser> usersDataBase;
    private DataBase<BaseContent> contentDataBase;
    private IdGenerator idGenerator;

    public ServerFacade() {
        usersDataBase = DataBaseFactory.getUserDataBaseInstance(Constants.USERS_FOLDER);
        contentDataBase = DataBaseFactory.getContentDataBaseInstance(Constants.CONTENTS_FOLDER);
        idGenerator = IdGenerator.getInstance();
    }


    public Response loginUser(Map<String, String> params) {
        int id = getId(params);
        Response response;
        BaseUser user;

        if (usersDataBase.contains(id)) {
            user = usersDataBase.get(id);
            if (user.authenticate(params.get("password"))) {
                if (user instanceof Admin) {
                    response = new Response("Admin\n" + user.toString(), ResponseStatus.Success);
                } else {
                    response = new Response("User\n" + user.toString(), ResponseStatus.Success);
                }
            } else {
                response = new Response("password mismatch", ResponseStatus.UnAuthorized);
            }
        } else {
            response = new Response("not found", ResponseStatus.NotFound);
        }
        return response;
    }

    private int getId(Map<String, String> params) {
        return Integer.parseInt(params.get("id"));
    }

    public Response getUser(Map<String, String> params) {
        int id = getId(params);
        BaseUser user;
        Response response;

        if (usersDataBase.contains(id)) {
            user = usersDataBase.get(id);
            response = new Response(user.toString(), ResponseStatus.Success);
        } else {
            response = new Response("not found", ResponseStatus.NotFound);
        }
        return response;
    }

    public Response createUser(Map<String, String> params) {
        int choice = Integer.parseInt(params.get("choice"));
        BaseUser user;
        if (choice == 0) {
            user = createNormalUser(params);
        } else {
            user = createAdmin(params);
        }
        usersDataBase.add(user);
        return new Response(user.toString(), ResponseStatus.Success);
    }

    private User createNormalUser(Map<String, String> params) {
        int userId = idGenerator.createUserIdentity();
        String userName = params.get("userName");
        String password = params.get("password");
        LocalDate timeStamp = LocalDate.now();
        int userPrivilege = Integer.parseInt(params.get("userPrivilege"));
        return new User(userId, userName, password, timeStamp, 0, UserPrivilege.getPrivilege(userPrivilege));
    }

    private Admin createAdmin(Map<String, String> params) {
        int userId = idGenerator.createUserIdentity();
        String userName = params.get("userName");
        String password = params.get("password");
        LocalDate timeStamp = LocalDate.now();
        int adminPrivilege = Integer.parseInt(params.get("adminPrivilege"));
        return new Admin(userId, userName, password, timeStamp, AdminPrivilege.getPrivilege(adminPrivilege));
    }

    public Response updateUser(Map<String, String> params) {
        int choice = Integer.parseInt(params.get("choice"));
        int id = getId(params);
        BaseUser user;
        String userName = params.get("userName");
        String password = params.get("password");
        LocalDate timeStamp = LocalDate.parse(params.get("timeStamp"));

        if (!usersDataBase.contains(id)) {
            return new Response("not found", ResponseStatus.NotFound);
        }
        if (choice == 0) {
            int licenseId = Integer.parseInt(params.get("licenseId"));
            int userPrivilege = Integer.parseInt(params.get("userPrivilege"));
            user = new User(id, userName, password, timeStamp, licenseId, UserPrivilege.getPrivilege(userPrivilege));
        } else {
            int adminPrivilege = Integer.parseInt(params.get("adminPrivilege"));
            user = new Admin(id, userName, password, timeStamp, AdminPrivilege.getPrivilege(adminPrivilege));
        }
        usersDataBase.update(user);
        return new Response(user.toString(), ResponseStatus.Success);
    }

    public Response deleteUser(Map<String, String> params) {
        int id = getId(params);
        usersDataBase.delete(id);
        return new Response("delete success", ResponseStatus.Success);
    }


    public Response createContent(Map<String, String> params) {
        int id = idGenerator.createContentIdentity();
        int authorId = Integer.parseInt(params.get("authorId"));
        int journalId = Integer.parseInt(params.get("journalId"));
        String title = params.get("title");
        String body = params.get("body");
        LocalDate timeStamp = LocalDate.now();
        BaseContent content = new PublicationContent(id, timeStamp, journalId, authorId, title, body);
        contentDataBase.add(content);
        return new Response(content.toString(), ResponseStatus.Success);
    }

//    public Response updateContent(Map<String, String> params) {
//        int id = getId(params);
//        int authorId = Integer.parseInt(params.get("authorId"));
//        int journalId = Integer.parseInt(params.get("journalId"));
//        String title = params.get("title");
//        String body = params.get("body");
//        LocalDate timeStamp = LocalDate.parse(params.get("timeStamp"));
//        int licenseId = Integer.parseInt("licenseId");
//        BaseLicense license;
//
//        if (!licenseDataBase.contains(licenseId)) {
//            return new Response("license not found", ResponseStatus.NotFound);
//        }
//        if (!contentDataBase.contains(id)) {
//            return new Response("publication not found", ResponseStatus.NotFound);
//        }
//        license = licenseDataBase.get(licenseId);
//
//        BaseContent content = new PublicationContent(id, timeStamp, journalId, authorId, title, body);
//        if (!license.canAccessContent(content)) {
//            return new Response("license can't access content", ResponseStatus.UnAuthorized);
//        }
//
//        contentDataBase.update(content);
//        return new Response(content.toString(), ResponseStatus.Success);
//    }

    public Response deleteContent(Map<String, String> params) {
        int id = getId(params);
        contentDataBase.delete(id);
        return new Response("delete success", ResponseStatus.Success);
    }

}
