package edu.ib;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StartSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button centrumLoginButton;

    @FXML
    private Button bankLoginButton;

    @FXML
    void bankLoginButtonClicked(ActionEvent event) throws IOException {
        fxmlLoad("BankKrwiLogin.fxml", "Logowanie do regionalnego banku krwi", event);
    }

    @FXML
    void centrumLoginButtonClicked(ActionEvent event) throws IOException {
        fxmlLoad("CentrumKrwiLogin.fxml", "Logowanie do centrum krwiodawstwa", event);
    }

    void fxmlLoad(String fxml, String title, ActionEvent event) throws IOException {
        Parent login_parent = FXMLLoader.load(getClass().getResource("/fxml/" + fxml));
        Scene login_scene = new Scene(login_parent, 800, 600);
        Stage login_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        login_stage.setScene(login_scene);
        login_stage.setTitle(title);
        login_stage.show();
    }

    @FXML
    void initialize() {
        assert centrumLoginButton != null : "fx:id=\"centrumLoginButton\" was not injected: check your FXML file 'edu.ib.StartScene.fxml'.";
        assert bankLoginButton != null : "fx:id=\"bankLoginButton\" was not injected: check your FXML file 'edu.ib.StartScene.fxml'.";

    }
}

