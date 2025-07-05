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
        int retries = 3;
        while (retries > 0) {
            try {
                realService.connect(host, port, model);
                System.out.println("Proxy: Connection successful.");
                return;
            } catch (Exception e) {
                retries--;
                System.out.println("Proxy: Connection failed. Retrying... (" + retries + " attempts left)");
                try {
                    Thread.sleep(2000); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("Proxy: Failed to connect after multiple attempts.");
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
