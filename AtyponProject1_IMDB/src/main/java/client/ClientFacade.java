package client;

import  operations.Operation;
import  operations.Request;
import  operations.Response;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClientFacade {
    private Scanner sc;

    public ClientFacade() {
        sc = new Scanner(System.in);
    }

    public void createUser() {
        Map<String, String> params = new HashMap<>();
        Response response;
        int choice;
        int privilege;
        String name;
        String password;

        System.out.print("0 for user 1 for Admin :");
        choice = sc.nextInt();
        params.put("choice", String.valueOf(choice));
        System.out.print("enter name: ");
        name = sc.next();
        params.put("userName", name);
        System.out.print("enter password: ");
        password = sc.next();
        params.put("password", password);
        System.out.println("0 for Base Privilege");
        System.out.println("1 for Premium Privilege");
        privilege = sc.nextInt();
        if (choice == 0) {
            params.put("userPrivilege", String.valueOf(privilege));
        } else if (choice == 1) {
            params.put("adminPrivilege", String.valueOf(privilege));
        }

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.CreateUser, params));
        System.out.println(response.getBody());
    }

    public void updateUser() {
        Map<String, String> params = new HashMap<>();
        Response response;
        int choice;
        int id;
        int privilege;
        int licenseId;
        String name;
        String password;
        LocalDate timeStamp;

        System.out.print("0 for user 1 for Admin :");
        choice = sc.nextInt();
        params.put("choice", String.valueOf(choice));

        System.out.print("enter id: ");
        id = sc.nextInt();
        params.put("id", String.valueOf(id));

        System.out.print("enter name: ");
        name = sc.next();
        params.put("userName", name);

        System.out.print("enter password: ");
        password = sc.next();
        params.put("password", password);

        timeStamp = LocalDate.now();
        params.put("timeStamp", timeStamp.toString());

        System.out.println("0 for Base Privilege");
        System.out.println("1 for Premium Privilege");
        privilege = sc.nextInt();
        if (choice == 0) {
            params.put("userPrivilege", String.valueOf(privilege));
            System.out.print("enter license id: ");
            licenseId = sc.nextInt();
            params.put("licenseId", String.valueOf(licenseId));
        } else if (choice == 1) {
            params.put("adminPrivilege", String.valueOf(privilege));
        }

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.UpdateUser, params));
        System.out.println(response.getBody());
    }

    public void getUser() {
        Map<String, String> params = new HashMap<>();
        Response response;

        System.out.print("id :");
        params.put("id", sc.next());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.GetUser, params));
        System.out.println(response.getBody());
    }

    public void deleteUser() {
        Map<String, String> params = new HashMap<>();
        Response response;

        System.out.print("id :");
        params.put("id", sc.next());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.DeleteUser, params));
        System.out.println(response.getBody());
    }

    public void createJournal() {
        Map<String, String> params = new HashMap<>();
        Response response;

        System.out.print("journal Name : ");
        params.put("journalName", sc.next());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.CreateJournal, params));
        System.out.println(response.getBody());
    }

    public void updateJournal() {
        Map<String, String> params = new HashMap<>();
        Response response;

        System.out.print("id :");
        params.put("id", sc.next());
        System.out.print("journal Name : ");
        params.put("journalName", sc.next());
        params.put("timeStamp", LocalDate.now().toString());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.UpdateJournal, params));
        System.out.println(response.getBody());
    }

    public void getJournal() {
        Map<String, String> params = new HashMap<>();
        Response response;

        System.out.print("id :");
        params.put("id", sc.next());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.GetJournal, params));
        System.out.println(response.getBody());
    }

    public void deleteJournal() {
        Map<String, String> params = new HashMap<>();
        Response response;
        System.out.print("id :");
        params.put("id", sc.next());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.DeleteJournal, params));
        System.out.println(response.getBody());
    }

    public void createContent() {
        Map<String, String> params = new HashMap<>();
        Response response;

        System.out.print("title :");
        params.put("title", sc.next());
        System.out.print("body :");
        params.put("body", sc.next());
        System.out.print("authorId :");
        params.put("authorId", sc.next());
        System.out.print("journalId :");
        params.put("journalId", sc.next());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.CreateContent, params));
        System.out.println(response.getBody());

    }

    public void updateContent() {
        Map<String, String> params = new HashMap<>();
        Response response;
        System.out.print("id :");
        params.put("id", sc.next());
        System.out.print("title :");
        params.put("title", sc.next());
        System.out.print("body :");
        params.put("body", sc.next());
        System.out.print("authorId :");
        params.put("authorId", sc.next());
        System.out.print("journalId :");
        params.put("journalId", sc.next());
        params.put("timeStamp", LocalDate.now().toString());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.UpdateContent, params));
        System.out.println(response.getBody());

    }

    public void getContent() {
        Map<String, String> params = new HashMap<>();
        Response response;
        System.out.print("id :");
        params.put("id", sc.next());
        System.out.print("licenseId :");
        params.put("licenseId", sc.next());

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.GetContent, params));
        System.out.println(response.getBody());
    }

    public void deleteContent() {
        Map<String, String> params = new HashMap<>();
        Response response;
        System.out.print("id :");
        params.put("id", sc.next());
        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.DeleteContent, params));
        System.out.println(response.getBody());
    }

    public void createLicence() {
        Map<String, String> params = new HashMap<>();
        Response response;
        int choice;
        int counter;
        int journalId;
        String endDate;

        System.out.println("0 for content license");
        System.out.println("1 for journal license");
        System.out.println("2 for date license");
        choice = sc.nextInt();
        params.put("choice", String.valueOf(choice));

        if (choice == 0) {
            System.out.print("how many contents: ");
            counter = sc.nextInt();
            params.put("counter", String.valueOf(counter));
            for (int i = 0; i < counter; ++i) {
                System.out.printf("enter content# %d: ", i);
                params.put(String.valueOf(i), sc.next());
            }
        } else if (choice == 1) {
            System.out.print("journal ID: ");
            journalId = sc.nextInt();
            params.put("journalId", String.valueOf(journalId));
        } else if (choice == 2) {
            System.out.print("end date in the following format yyyy-mm-dd: ");
            endDate = sc.next();
            params.put("endDate", endDate);
        }

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.CreateLicense, params));
        System.out.println(response.getBody());
    }

    public void updateLicence() {
        Map<String, String> params = new HashMap<>();
        Response response;
        int choice;
        int counter;
        int journalId;
        int id;
        String endDate;
        LocalDate timeStamp;

        System.out.println("0 for content license");
        System.out.println("1 for journal license");
        System.out.println("2 for date license");
        choice = sc.nextInt();
        params.put("choice", String.valueOf(choice));

        System.out.print("enter id: ");
        id = sc.nextInt();
        params.put("id", String.valueOf(id));

        timeStamp = LocalDate.now();
        params.put("timeStamp", timeStamp.toString());


        if (choice == 0) {
            System.out.print("how many contents: ");
            counter = sc.nextInt();
            params.put("counter", String.valueOf(counter));
            for (int i = 0; i < counter; ++i) {
                System.out.printf("enter content# %d: ", i);
                params.put(String.valueOf(i), sc.next());
            }
        } else if (choice == 1) {
            System.out.print("journal ID: ");
            journalId = sc.nextInt();
            params.put("journalId", String.valueOf(journalId));
        } else if (choice == 2) {
            System.out.print("end date in the following format yyyy-mm-dd: ");
            endDate = sc.next();
            params.put("endDate", endDate);
        }

        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.UpdateLicense, params));
        System.out.println(response.getBody());
    }

    public void getLicence() {
        Map<String, String> params = new HashMap<>();
        Response response;
        System.out.print("id : ");
        params.put("id", sc.next());
        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.GetLicense, params));
        System.out.println(response.getBody());
    }

    public void deleteLicence() {
        Map<String, String> params = new HashMap<>();
        Response response;
        System.out.print("id : ");
        params.put("id", sc.next());
        response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.DeleteLicense, params));
        System.out.println(response.getBody());
    }
}
