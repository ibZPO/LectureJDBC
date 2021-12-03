package edu.ib;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MovieAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConnect;

    @FXML
    private Button btnDisconnect;

    @FXML
    private TextArea txtAreaDBStatus;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUser;

    @FXML
    void btnConnectPressed(ActionEvent event) {

    }

    @FXML
    void btnDisconnectPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnConnect != null : "fx:id=\"btnConnect\" was not injected: check your FXML file 'movieapp.fxml'.";
        assert btnDisconnect != null : "fx:id=\"btnDisconnect\" was not injected: check your FXML file 'movieapp.fxml'.";
        assert txtAreaDBStatus != null : "fx:id=\"txtAreaDBStatus\" was not injected: check your FXML file 'movieapp.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'movieapp.fxml'.";
        assert txtUser != null : "fx:id=\"txtUser\" was not injected: check your FXML file 'movieapp.fxml'.";

    }

}
