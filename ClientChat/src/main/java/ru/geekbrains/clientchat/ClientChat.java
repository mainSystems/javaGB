package ru.geekbrains.clientchat;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.geekbrains.clientchat.controllers.AuthController;
import ru.geekbrains.clientchat.controllers.ClientController;

import java.io.IOException;

import static ru.geekbrains.clientchat.Network.SERVER_ADDR;


public class ClientChat extends Application {

    private Stage chatStage;
    private Stage authStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.chatStage = primaryStage;

        ClientController controller = createChatDialog(primaryStage);
        connectToServer(controller);

        createAuthDialog(primaryStage);

        controller.initMessageHandler();

    }

    private ClientController createChatDialog(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ClientChat.class.getResource("chat-template.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        this.chatStage.setTitle("Chat!");
        this.chatStage.setScene(scene);

        ClientController controller = fxmlLoader.getController();
        controller.userList.getItems().addAll("user1", "user2");

        primaryStage.show();

        return controller;
    }

    private void createAuthDialog(Stage primaryStage) throws IOException {
        FXMLLoader authLoader = new FXMLLoader();
        authLoader.setLocation(ClientChat.class.getResource("authDialog.fxml"));
        AnchorPane authDialogPanel = authLoader.load();

        authStage = new Stage();
        authStage.initOwner(primaryStage);
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.setScene(new Scene(authDialogPanel));
        AuthController authController = authLoader.getController();
        authController.setClientChat(this);
        authController.initMessageHandler();
        authStage.showAndWait();
    }

    private void connectToServer(ClientController clientController) {
        boolean resultConnectToServer = Network.getInstance().connect();
        if (!resultConnectToServer) {
            showErrorDialog(ErrorMessage.CANT_CONNECT_TO_SERVER);
            System.err.printf("%s: %s%n", ErrorMessage.CANT_CONNECT_TO_SERVER, SERVER_ADDR);
        }


        clientController.setApplication(this);

        chatStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Network.getInstance().close();
            }
        });
    }

    public void showErrorDialog(ErrorMessage errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(String.valueOf(errorMessage));
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getAuthStage() {
        return authStage;
    }

    public Stage getChatStage() {
        return chatStage;
    }
}