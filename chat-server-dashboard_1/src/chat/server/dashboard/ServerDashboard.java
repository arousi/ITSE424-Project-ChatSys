/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.server.dashboard;
import core.ServerCore;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ServerDashboard extends Application {
    private TextArea messageLog = new TextArea();
    private ListView<String> clientList = new ListView<>();
    private Button terminateClientBtn = new Button("Terminate Client");
    private Button stopServerBtn = new Button("Stop Server");
    private Button restartServerBtn = new Button("Restart Server");

    private ServerCore server;

    @Override
    public void start(Stage primaryStage) {
        messageLog.setEditable(false);

        VBox controls = new VBox(10, terminateClientBtn, stopServerBtn, restartServerBtn);
        HBox layout = new HBox(10, new VBox(new Label("Clients"), clientList), new VBox(new Label("Messages"), messageLog), controls);
        layout.setStyle("-fx-padding: 10;");

        terminateClientBtn.setOnAction(e -> terminateSelectedClient());
        stopServerBtn.setOnAction(e -> stopServer());
        restartServerBtn.setOnAction(e -> restartServer());

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chat Server Dashboard");
        primaryStage.show();

        startServer();
    }

    private void startServer() {
        server = new ServerCore(this);
        new Thread(server).start();
    }

    private void stopServer() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void restartServer() {
        stopServer();
        startServer();
    }

    private void terminateSelectedClient() {
        String selected = clientList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            server.terminateClient(selected);
        }
    }

    // Public API for ServerCore to update the GUI
    public void logMessage(String message) {
        Platform.runLater(() -> messageLog.appendText(message + "\n"));
    }

    public void addClient(String id) {
        Platform.runLater(() -> clientList.getItems().add(id));
    }

    public void removeClient(String id) {
        Platform.runLater(() -> clientList.getItems().remove(id));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

