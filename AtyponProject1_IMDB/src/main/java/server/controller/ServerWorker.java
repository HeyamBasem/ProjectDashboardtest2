package server.controller;

import operations.Request;
import operations.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerWorker implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ServerFacade facade;


    public ServerWorker(Socket socket) {
        initializeConnection(socket);
        facade = new ServerFacade();
    }

    private void initializeConnection(Socket socket) {
        try {
            initializeConnectionWithException(socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeConnectionWithException(Socket socket) throws Exception {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        Request request = readRequest();
        Response response;

        if (request == null) {
            response = Response.serverErrorResponse();
        } else {
            switch (request.getOperation()) {
                case LoginUser:
                    response = facade.loginUser(request.getParams());
                    break;
                case GetUser:
                    response = facade.getUser(request.getParams());
                    break;
                case CreateUser:
                    response = facade.createUser(request.getParams());
                    break;
                case UpdateUser:
                    response = facade.updateUser(request.getParams());
                    break;
                case DeleteUser:
                    response = facade.deleteUser(request.getParams());
                    break;

                case GetJournal:
                    response = facade.getJournal(request.getParams());
                    break;
                case CreateJournal:
                    response = facade.createJournal(request.getParams());
                    break;
                case UpdateJournal:
                    response = facade.updateJournal(request.getParams());
                    break;
                case DeleteJournal:
                    response = facade.deleteJournal(request.getParams());
                    break;

                case GetContent:
                    response = facade.getContent(request.getParams());
                    break;
                case CreateContent:
                    response = facade.createContent(request.getParams());
                    break;
                case UpdateContent:
                    response = facade.updateContent(request.getParams());
                    break;
                case DeleteContent:
                    response = facade.deleteContent(request.getParams());
                    break;

                case GetLicense:
                    response = facade.getLicense(request.getParams());
                    break;
                case CreateLicense:
                    response = facade.createLicense(request.getParams());
                    break;
                case UpdateLicense:
                    response = facade.updateLicense(request.getParams());
                    break;
                case DeleteLicense:
                    response = facade.deleteLicense(request.getParams());
                    break;
                default:
                    response = Response.serverErrorResponse();
            }
        }
        sendResponse(response);
        closeConnection();
    }

    private Request readRequest() {
        try {
            return readRequestWithException();
        } catch (Exception e) {
            return null;
        }
    }

    private Request readRequestWithException() throws Exception {
        return (Request) in.readObject();
    }

    private void sendResponse(Response response) {
        try {
            sendResponseWithException(response);
        } catch (Exception e) {
        }
    }

    private void sendResponseWithException(Response response) throws Exception {
        out.writeObject(response);
        out.flush();
    }

    private void closeConnection() {
        try {
            closeConnectionWithException();
        } catch (Exception e) {
        }
    }

    private void closeConnectionWithException() throws Exception {
        in.close();
        out.close();
        socket.close();
    }

}
