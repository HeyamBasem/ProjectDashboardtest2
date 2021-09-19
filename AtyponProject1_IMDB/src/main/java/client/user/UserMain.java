package client.user;

import client.ClientFacade;
import client.SocketConnection;
import operations.Operation;
import operations.Request;
import operations.Response;
import operations.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserMain {
    private static void showMenu() {

        System.out.println("1.Update Journal");
        System.out.println("2.Read Journal");

        System.out.println("3.Update Content");
        System.out.println("4.Read Content");

        System.out.println("5.Exit");
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
                    facade.updateJournal();
                    break;
                case 2:
                    facade.getJournal();
                    break;
                case 3:
                    facade.updateContent();
                    break;
                case 4:
                    facade.getContent();
                    break;
                case 5:
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
            System.out.print("enter id: ");
            params.put("id", sc.next());
            System.out.print("enter admin password: ");
            params.put("password", sc.next());
            response = SocketConnection.sendRequestAndReceiveResponse(new Request(Operation.LoginUser, params));
            if (response.getStatus() != ResponseStatus.Success) {
                System.out.println(response.getBody());
            }
        } while (response.getStatus() != ResponseStatus.Success && !response.getBody().equalsIgnoreCase("user"));
    }

    public static void main(String[] args) {
        System.out.println("welcome to user application");
        logIn();
        System.out.println("login successful");
        loop();
    }
}
