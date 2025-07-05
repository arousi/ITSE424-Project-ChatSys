/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final String id;
    private final ServerCore server;
    private PrintWriter out;
    private BufferedReader in;
    private boolean active = true;
    private final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public ClientHandler(Socket socket, String id, ServerCore server) {
        this.socket = socket;
        this.id = id;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while (active && (line = in.readLine()) != null) {
                server.broadcast("[" + id + "]: " + line);
            }
        } catch (IOException e) {
            server.broadcast("Client " + id + " disconnected due to an error: " + e.getMessage());
        } finally {
            stop();
            server.broadcast("Client " + id + " cleanup completed.");
        }
    }

    public void send(String message) {
        if (out != null) out.println(message);
    }

    public void stop() {
        try {
            active = false;
            if (socket != null) socket.close();
            server.broadcast("Client " + id + " forcibly closed.");
        } catch (IOException e) {
            server.broadcast("Error closing client " + id);
        }
    }

    public String getId() {
        return id;
    }
}

