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
import java.time.LocalDateTime;

public class ChatController {
    public ChatController(ChatModel model, ChatView view, ChatService proxy) {
        // Button click
        view.sendButton.setOnAction(e -> sendMessage(model, view, proxy));

        view.reconnectButton.setOnAction(e -> {
            proxy.connect("localhost", 1234, model); // or server IP
            model.addMessage("System", LocalDateTime.now().toString(), "Attempting to reconnect...");
        });
        
        // Enter key press
        view.inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage(model, view, proxy);
            }
        });

        // PropertyChangeListener for updating the chat area
        model.addPropertyChangeListener(evt -> {
            if ("message".equals(evt.getPropertyName())) {
                view.chatArea.appendText(evt.getNewValue().toString() + "\n");
            }
        });
    }

    private void sendMessage(ChatModel model, ChatView view, ChatService proxy) {
        String msg = view.inputField.getText().trim(); // trim to avoid sending spaces
        if (!msg.isEmpty()) {
            String fullMsg = MessageFactory.createMessage("You", msg);
            model.addMessage("You", LocalDateTime.now().toString(), msg);
            proxy.sendMessage(msg);
            view.inputField.clear();
        }
    }
}
