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


// Proxy Pattern
import model.ChatModel;

public class ClientProxy implements ChatService {
    private final RealChatService realService = new RealChatService();

    @Override
    public void connect(String host, int port, ChatModel model) {
        System.out.println("Proxy: Connecting to server...");
        realService.connect(host, port, model);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Proxy: Sending message -> " + message);
        realService.sendMessage(message);
    }

    @Override
    public void receiveAsync(ChatModel model) {
        realService.receiveAsync(model);
    }
}
