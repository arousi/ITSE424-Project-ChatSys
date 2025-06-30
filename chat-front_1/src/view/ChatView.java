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
        VBox root = new VBox(10, chatArea, inputField, hbox1);
        root.setStyle("-fx-padding: 10;");
        return root;
    }
}
