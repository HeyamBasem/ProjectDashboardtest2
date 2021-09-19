package server.view;

import  operations.Constants;
import server.controller.ServerWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    public static void main(String[] args) {
        try {
            runServer();
        } catch (IOException e) {
            // normal the client close the connection
        }
    }

    private static void runServer() throws IOException {
        Socket socket;
        ServerSocket serverSocket = new ServerSocket(Constants.PORT);
        ExecutorService executorService = Executors.newCachedThreadPool();

        while ((socket = serverSocket.accept()) != null) {
            executorService.submit(new ServerWorker(socket));
        }
    }
}
