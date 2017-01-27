package server;

import manager.SocketManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by evgenyp on 1/23/2017.
 */
public class WebServer implements IServer {
    private int port;
    ServerSocket serverSocket;

    public WebServer(int port) {
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started, ip: " + serverSocket.getLocalPort() + ", inet dares: " +
                serverSocket.getInetAddress() + "<------");
        while (serverSocket != null) {
            Socket socket = serverSocket.accept();
            Thread th = new Thread(new SocketManager(socket));
            th.start();
        }
    }

    @Override
    public void stop() throws IOException {
        if (serverSocket != null) {
            System.out.println("Server stoped, ip: " + serverSocket.getLocalPort() + ", inet dares: " +
                    serverSocket.getInetAddress() + "------>");
            serverSocket.close();
            serverSocket = null;
        }
    }

    @Override
    public void restart() throws IOException {
        stop();
        start();
    }
}
