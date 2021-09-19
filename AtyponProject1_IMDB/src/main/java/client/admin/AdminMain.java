package client.admin;

import  client.ClientFacade;
import  client.SocketConnection;
import  operations.Operation;
import  operations.Request;
import  operations.Response;
import  operations.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminMain {
    private static void showMenu() {
        System.out.println("1.Create User");
        System.out.println("2.Update User");
        System.out.println("3.Read User");
        System.out.println("4.Delete User");

        System.out.println("5.Create Journal");
        System.out.println("6.Update Journal");
        System.out.println("7.Read Journal");
        System.out.println("8.Delete Journal");

        System.out.println("9.Create Content");
        System.out.println("10.Update Content");
        System.out.println("11.Read Content");
        System.out.println("12.Delete Content");

        System.out.println("13.Create License");
        System.out.println("14.Update License");
        System.out.println("15.Read License");
        System.out.println("16.Delete License");

        System.out.println("17.Exit");
    }

    private static void loop() {
        Scanner sc = new Scanner(System.in);
        int choice;
        ClientFacade facade = new ClientFacade();
        while (true) {
            showMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    facade.createUser();
                    break;
                case 2:
                    facade.updateUser();
                    break;
                case 3:
                    facade.getUser();
                    break;
                case 4:
                    facade.deleteUser();
                    break;
                case 5:
                    facade.createJournal();
                case 6:
                    facade.updateJournal();
                    break;
                case 7:
                    facade.getJournal();
                    break;
                case 8:
                    facade.deleteJournal();
                    break;
                case 9:
                    facade.createContent();
                    break;
                case 10:
                    facade.updateContent();
                    break;
                case 11:
                    facade.getContent();
                    break;
                case 12:
                    facade.deleteContent();
                    break;
                case 13:
                    facade.createLicence();
                    break;
                case 14:
                    facade.updateLicence();
                    break;
                case 15:
                    facade.getLicence();
                    break;
                case 16:
                    facade.deleteLicence();
                    break;
                case 17:
                default:
                    return;
            }
        }
    }

    private static void logIn() {
        Response response;
        Map<String, String> params = new HashMap<String, String>();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("enter admin id: ");
            params.put("id", sc.next());
            System.out.print("enter admin password: ");
            params.put("password", sc.next());
            response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.LoginUser, params));
            if (response.getStatus() != ResponseStatus.Success) {
                System.out.println(response.getBody());
            }
        } while (response.getStatus() != ResponseStatus.Success && !response.getBody().equalsIgnoreCase("admin"));
    }

    public static void main(String[] args) {
        System.out.println("welcome to admin application");
        logIn();
        System.out.println("login successful");
        loop();
    }
}
