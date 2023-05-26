package server;

import dto.Parameters;

import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public Server() {

    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(3345, 1);
            while (!server.isClosed()) {
                Socket client = server.accept();
                System.out.println("Client accept");
                Parameters.setConnected(1);
                ClientHandler clientThread = new ClientHandler(client);
                clientThread.start();
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createServer() {
        try {
            ServerSocket server = new ServerSocket(3345, 1);
            Socket client = server.accept();
            ClientHandler clientThread = new ClientHandler(client);
            clientThread.start();
            /*while (!server.isClosed()) {
                Socket client = server.accept();
                System.out.println("Client accept");
                ClientHandler clientThread = new ClientHandler(client);
                clientThread.start();
            }*/
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
