package core;

import chat.server.dashboard.ServerDashboard;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerCore implements Runnable {
    private final int PORT = 1234;
    private ServerSocket serverSocket;
    private final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();
    private final ServerDashboard dashboard;
    private boolean running = true;

    public ServerCore(ServerDashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            dashboard.logMessage("Server started on port " + PORT);

            while (running) {
                Socket socket = serverSocket.accept();
                String id = socket.getRemoteSocketAddress().toString();
                ClientHandler handler = new ClientHandler(socket, id, this);
                clients.put(id, handler);
                dashboard.addClient(id);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            if (running) dashboard.logMessage("Server error: " + e.getMessage());
        }
    }

    public void shutdown() {
        running = false;
        try {
            for (ClientHandler ch : clients.values()) {
                ch.stop();
            }
            if (serverSocket != null) serverSocket.close();
            dashboard.logMessage("Server stopped.");
            clients.clear();
        } catch (IOException e) {
            dashboard.logMessage("Error stopping server: " + e.getMessage());
        }
    }

    public void terminateClient(String id) {
        ClientHandler handler = clients.get(id);
        if (handler != null) {
            handler.stop();
            clients.remove(id);
            dashboard.removeClient(id);
            dashboard.logMessage("Client " + id + " terminated.");
        }
    }

    public void broadcast(String msg) {
        dashboard.logMessage("Broadcast: " + msg);
        for (ClientHandler handler : new ArrayList<>(clients.values())) {
            handler.send(msg);
        }
    }
}
