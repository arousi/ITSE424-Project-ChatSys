/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Sanad
 */


import model.MessageFactory;
import network.ChatService;
import view.ChatView;
import model.ChatModel;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.input.KeyCode;

public class ChatController {
    public ChatController(ChatModel model, ChatView view, ChatService proxy) {
        // Button click
        view.sendButton.setOnAction(e -> sendMessage(model, view, proxy));

        view.reconnectButton.setOnAction(e -> {
            proxy.connect("localhost", 1234, model); // or server IP
            model.addMessage("[System] Attempting to reconnect...");
        });
        
        // Enter key press
        view.inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage(model, view, proxy);
            }
        });

        // Observer for updating the chat area
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                view.chatArea.appendText(arg.toString() + "\n");
            }
        });
    }
    
    

    private void sendMessage(ChatModel model, ChatView view, ChatService proxy) {
        String msg = view.inputField.getText().trim(); // trim to avoid sending spaces
        if (!msg.isEmpty()) {
            String fullMsg = MessageFactory.createMessage("You", msg);
            model.addMessage(fullMsg);
            proxy.sendMessage(msg);
            view.inputField.clear();
        }
    }
}
