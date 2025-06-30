/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Sanad
 */
public class Client {
    private static Client instance;
    private Socket socket;
    private PrintWriter out;

    private Client() { }

    public static Client getInstance() {
        if (instance == null) instance = new Client();
        return instance;
    }

    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        // Thread to listen to incoming messages can be added here
    }

    public void send(String message) {
        if (out != null) out.println(message);
    }
}
