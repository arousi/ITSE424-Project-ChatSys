/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author Sanad
 */

import model.ChatModel;
import java.io.*;
import java.net.Socket;

public class RealChatService implements ChatService {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String myAddress;
    private ChatModel model;
    @Override
    public void connect(String host, int port, ChatModel model) {
        this.model = model;
    try {
        socket = new Socket(host, port);
        myAddress = socket.getLocalSocketAddress().toString();  // 👈 Store your own identity
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        

        new Thread(() -> {
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    // 👇 Skip messages the client sent itself
                    if (!line.contains(myAddress)) {
                        model.addMessage("[Peer] " + line);
                    }
                }
            } catch (IOException e) {
                model.addMessage("[System] Disconnected from server.");
            }
        }).start();

    } catch (IOException e) {
        model.addMessage("[Error] " + e.getMessage());
    }
}

    @Override
    public void sendMessage(String message) {
        if (out != null) out.println(message);
    }

    @Override
    public void receiveAsync(ChatModel model) {
        this.model = model;
        Thread listener = new Thread(() -> {
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    String fullMsg = "[Peer] " + line;
                    this.model.addMessage(fullMsg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        listener.setDaemon(true);
        listener.start();
    }
}