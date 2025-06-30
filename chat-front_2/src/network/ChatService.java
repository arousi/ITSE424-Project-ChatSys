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

public interface ChatService {
    void connect(String host, int port, ChatModel model);
    void sendMessage(String message);
    void receiveAsync(ChatModel model);
}
