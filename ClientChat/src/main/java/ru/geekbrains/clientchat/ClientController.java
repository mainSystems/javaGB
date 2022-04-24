package ru.geekbrains.clientchat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.DateFormat;
import java.util.Date;

public class ClientController {

    @FXML
    public TextField messageField;
    @FXML
    public Button sendMessageButton;
    @FXML
    public TextArea messageArea;
    @FXML
    public ListView userList;

    public void appendMessageToChat(ActionEvent actionEvent) {
        if (!messageField.getText().isEmpty()){


            messageArea.appendText(DateFormat.getDateInstance().format(new Date()) + ": ");
            messageArea.appendText(messageField.getText().trim());
            if (!userList.getSelectionModel().isEmpty()){
                String sender = userList.getSelectionModel().getSelectedItems().toString();
                messageArea.appendText(" -> " + sender);
            }
            messageArea.appendText(System.lineSeparator());
            messageArea.appendText(System.lineSeparator());
            messageField.setFocusTraversable(true);
            messageField.requestFocus();
            messageField.clear();
        }
    }
}