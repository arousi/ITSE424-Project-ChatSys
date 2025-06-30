/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import controller.ChatController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ChatModel;
import network.ClientProxy;
import view.ChatView;


/**
 *
 * @author Sanad
 */

 //JavaFX
public class ChatApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ChatModel model = new ChatModel();
        ChatView view = new ChatView();
        ClientProxy proxy = new ClientProxy();
        ChatController controller = new ChatController(model, view, proxy);

        primaryStage.setScene(new Scene(view.getLayout(), 400, 300));
        primaryStage.setTitle("Client 2");
        primaryStage.show();

        proxy.connect("localhost", 1234, model);
        proxy.receiveAsync(model);
    }

    public static void main(String[] args) {
        launch(args);
    }
}