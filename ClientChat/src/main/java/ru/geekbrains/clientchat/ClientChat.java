package ru.geekbrains.clientchat;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static ru.geekbrains.clientchat.Network.SERVER_ADDR;


public class ClientChat extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ClientChat.class.getResource("chat-template.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        this.stage.setTitle("Chat!");
        this.stage.setScene(scene);

        ClientController controller = fxmlLoader.getController();
        controller.userList.getItems().addAll("user1", "user2");

        stage.show();

        connectToServer(controller);

    }

    private void connectToServer(ClientController clientController) {
        Network network = new Network();

        if (!network.connect()) {
            showErrorDialog(ErrorMessage.Cant_Connect_to_server);
            System.err.printf("%s: %s%n", ErrorMessage.Cant_Connect_to_server, SERVER_ADDR);
        }

        clientController.setNetwork(network);
        clientController.setApplication(this);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                network.close();
            }
        });
    }

    protected void showErrorDialog(ErrorMessage errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(String.valueOf(errorMessage));
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}