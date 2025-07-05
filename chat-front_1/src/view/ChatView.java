/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Sanad
 */


//Observer Pattern 

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ChatView {
    public TextArea chatArea = new TextArea();
    public TextField inputField = new TextField();
    public Button sendButton = new Button("Send");
    public Button reconnectButton = new Button("Reconnect");
    
    HBox hbox1 = new HBox(sendButton, reconnectButton);
    public VBox getLayout() {
        chatArea.setEditable(false);
        chatArea.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-background-color: #ffffff; -fx-border-color: #cccccc;");

        inputField.setPromptText("Type your message here...");
        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 200) {
                inputField.setText(oldValue); // Limit message length to 200 characters
            }
        });

        Label header = new Label("Chat System");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10; -fx-background-color: #f0f0f0;");

        Label columnHeaders = new Label(String.format("%-15s %-25s %-25s", "Sender", "Message", "Timestamp"));
        columnHeaders.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 5; -fx-background-color: #e0e0e0;");

        VBox root = new VBox(10, header, columnHeaders, chatArea, inputField, hbox1);
        root.setStyle("-fx-padding: 10; -fx-background-color: #f9f9f9;");
        return root;
    }
}
